package exam.gold.nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.text.SimpleDateFormat;
import java.util.Properties;

//FileやNIOを理解するサンプルプログラム
public class FileSample {

    public static void main(String[] args) throws IOException {
        File f1 = new File("resouces");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        if (f1.isDirectory()) {
            System.out.println("ディレクトリ");
        }
        System.out.println(f1.getName());
        System.out.println(f1.getAbsolutePath());
        System.out.println(f1.getCanonicalPath());
        System.out.println("ラインセパレータ：" + System.lineSeparator() + "END");
        System.out.println("ファイルセパレータ：" + System.getProperty("file.separator"));
        System.out.println("ファイルセパレータ：" + System.getProperty("path.separator"));

        File file1 = new File("resouces/sample.txt");//UNIX形式でもなんか大丈夫！
        File file2 = new File(f1, "sample2.obj");
        char[] chars = new char[1024 * 10];
        if (!(file1.exists() && file2.exists())) {
            file1.createNewFile();
            file2.createNewFile();
            for (String files : f1.list()) {
                System.out.println(files);
            }

            //文字列　出力サンプル１
            try (OutputStream fos1 = new FileOutputStream(file1);
                    OutputStream bos1 = new BufferedOutputStream(fos1);
                    PrintStream ps1 = new PrintStream(bos1);
                    OutputStreamWriter writer1 = new OutputStreamWriter(bos1)) {
                //プロパティ
                Properties prps = System.getProperties();
                prps.list(ps1);
            }
            System.out.println("----------------------");
            //文字列　出力サンプル２
            try (CharArrayWriter cwriter = new CharArrayWriter();
                    BufferedWriter bwriter = new BufferedWriter(cwriter)) {
                bwriter.write("test");
                bwriter.newLine();
                bwriter.write("sample");
                bwriter.newLine();
                bwriter.write("こんにちは世界");
                bwriter.flush();
                try (CharArrayReader creader = new CharArrayReader(cwriter.toCharArray());
                        BufferedReader breader = new BufferedReader(creader)) {
                    String line;
                    while ((line = breader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            }
            System.out.println("----------------------");
            try (ByteArrayOutputStream bOutput1 = new ByteArrayOutputStream();
                    BufferedOutputStream bOut1 = new BufferedOutputStream(bOutput1);
                    DataOutputStream dOutput = new DataOutputStream(bOut1);
                    ObjectOutputStream oOut1 = new ObjectOutputStream(bOutput1)) {
                bOut1.write(new byte[] { (byte) 0x00, (byte) 0xFF, (byte) 0x00, (byte) 0xFF });
                bOut1.flush();
                dOutput.writeInt(Integer.MAX_VALUE);
                dOutput.writeInt(Integer.MIN_VALUE);
                dOutput.flush();
                //ObjectStreamで開くと、AC ED 00 05のバイト列が固定で付与される。
                oOut1.writeObject("ABCDEF");
                oOut1.writeObject(file1);
                oOut1.flush();
                try (ByteArrayInputStream bInput = new ByteArrayInputStream(bOutput1.toByteArray());
                        BufferedInputStream bio = new BufferedInputStream(bInput)) {
                    int b;
                    while ((b = bio.read()) != -1) {
                        System.out.print(b + ",");
                    }

                }
            }
            System.out.println("");
            System.out.println("----------------------");
            Console con = System.console();
            if (con != null) {
                //Eclipseでは使用できない。。。。。
                String readStr = con.readLine();
                System.out.println(readStr);

            }

            System.out.println("----------------------");
            //ここからNIO2
            Path path1 = Paths.get("resouces");
            Path path2 = Paths.get("resouces", "pathSample1");
            Path path3 = FileSystems.getDefault().getPath("resouces", "pathSample2");
            System.out.println(path3.toAbsolutePath().toString());

            Path parent = path1.getParent();
            if (parent != null) {
                Files.walk(parent).forEach(System.out::println);
            } else {
                System.out.println("parentなし");
            }

            //絶対パスを取得しておかないと、親が取れない。。。。
            parent = path1.toAbsolutePath().getParent();
            if (parent != null) {
                Files.walk(parent).forEach(System.out::println);
                Files.find(parent, 5, (p, attr) -> p.toString().endsWith("class"))
                        .forEach(f -> showAttr(f));
            }
            long time = file1.lastModified();
            System.out.println(sdf.format(time));
            file1.delete();
            file2.delete();
        }
        {
            //作成
            Path path1 = Paths.get("resouces","sample.txt");
            Files.createFile(path1);
            //すでに作成済み
            try{
            Files.createFile(path1);
            }catch(IOException ex){
                ex.printStackTrace();
            }
            //更新
            //更新ファイルがない


            //コピー
            //コピー先がある

            //削除
            Files.delete(path1);
            //削除するファイルがない


            //移動
            //移動先がない
            //移動先に同一ファイルがある

            //検索
            //ファイル内検索

            //ディレクトリ内のファイル検索
        }
    }

    private static void showAttr(Path p) {
        try {
            //こいつは読み取りだけ
            BasicFileAttributes attr = Files.readAttributes(p, BasicFileAttributes.class);
            System.out.println("size            : " + attr.size()); // 各メソッドで属性にアクセス
            System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
            System.out.println("lastAccessTime   : " + attr.lastAccessTime());
            System.out.println("creationTime   : " + attr.creationTime());
            System.out.println("isRegularFile   : " + attr.isRegularFile());
            System.out.println("isDirectory   : " + attr.isDirectory());
            //System.out.println("isArchive       : " + attr.isArchive());;

            //Viewｆで属性の変更ができる
            DosFileAttributeView attrView = Files.getFileAttributeView(p, DosFileAttributeView.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

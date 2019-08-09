package thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 使用Jdk提供的，管道流(pipe stream)实现线程间通信
 * <p>
 * 先开启read管道，没有read到数据的时候，会产生阻塞。
 * 直到管道中有对应的数据
 * <p>
 * 如果是字符流，可以考虑使用PipeWriter和PipeReader
 */
class WriteData {
    public void write(PipedOutputStream outputStream) {
        String data = "CN no.1 12345678901234567890 abcdefghijkllllllmmz";
        try {
            outputStream.write(data.getBytes());
            System.out.println("write data--->ok");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadData {
    public void read(PipedInputStream inputStream) {
        try {
            byte[] data = new byte[20];
            int length = inputStream.read(data);
            //说明有数据
            while (length != -1) {
                //tip 读取到有效长度即可
                String content = new String(data, 0, length);
                System.out.println("read--->" + content);
                length = inputStream.read(data);
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class Thead01 extends Thread {

    private final WriteData writeData;
    private final PipedOutputStream outputStream;

    public Thead01(WriteData writeData, PipedOutputStream outputStream) {
        this.writeData = writeData;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        super.run();

        writeData.write(outputStream);
    }
}

class Thread02 extends Thread {

    private final ReadData readData;
    private final PipedInputStream inputStream;

    public Thread02(ReadData readData, PipedInputStream inputStream) {
        this.readData = readData;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        super.run();

        readData.read(inputStream);
    }
}


public class Test04 {
    public static void main(String[] args) {
        try {
            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();
            //关联一下
            inputStream.connect(outputStream);

            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

            new Thread02(readData, inputStream).start();
            Thread.sleep(1000);
            new Thead01(writeData, outputStream).start();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

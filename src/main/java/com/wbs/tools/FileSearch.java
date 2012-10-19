package com.wbs.tools;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class FileSearch {

	static int i = 0;

	public static void searchFile(String path) throws IOException {

		File f = new File(path);
		if (f.isDirectory()) {
			File[] fList = f.listFiles();

			for (File file : fList) {
				if (file.isDirectory()) {
					searchFile(file.getPath());
				} else {
					if (file.getName().endsWith(".jar")) {
						System.out.println(file.getName());
						copyFile(file, new File("D:/test/work/"+file.getName()));
					}
				}
			}
		}
	}

	// 复制文件
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	// 复制文件夹
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

    /**
     * 
     * @param srcFileName
     * @param destFileName
     * @param srcCoding
     * @param destCoding
     * @throws IOException
     */
    public static void copyFile(File srcFileName, File destFileName, String srcCoding, String destCoding) throws IOException {// 把文件转换为GBK文件
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(srcFileName), srcCoding));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFileName), destCoding));
            char[] cbuf = new char[1024 * 5];
            int len = cbuf.length;
            int off = 0;
            int ret = 0;
            while ((ret = br.read(cbuf, off, len)) > 0) {
                off += ret;
                len -= ret;
            }
            bw.write(cbuf, 0, off);
            bw.flush();
        } finally {
            if (br != null)
                br.close();
            if (bw != null)
                bw.close();
        }
    }

    /**
     * 删除目录
     * @param filepath
     * @throws IOException
     */
    public static void del(String filepath) throws IOException {
        File f = new File(filepath);// 定义文件路径
        if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
            if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
                f.delete();
            } else {// 若有则把文件放进数组，并判断是否有下级目录
                File delFile[] = f.listFiles();
                int i = f.listFiles().length;
                for (int j = 0; j < i; j++) {
                    if (delFile[j].isDirectory()) {
                        del(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
                    }
                    delFile[j].delete();// 删除文件
                }
            }
        }
    }
    
	public static void main(String[] args) throws IOException {
		String path = "D:/test/springside4-jar/1.txt";
		Set<String> set = readFile(path);
		
		searchFile("D:/maven/work",set);
		
	}

	/**
	 * 读取文件中包含的文件名
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static Set<String> readFile(String path) throws IOException{
		File file = new File(path);
		
		FileReader reader = new FileReader(file);
		
		BufferedReader bufferedReader = new BufferedReader(reader);

		Set<String> set = new HashSet<String>();
		
		while (true){
			String t = bufferedReader.readLine();
			if(t==null || "".equals(t)){
				break;
			}
			String fileName = t;
			set.add(fileName);
//			System.out.println(fileName);
		}
		
		return set;
	}
	
	/**
	 * 提取set中指出的文件
	 * @param path
	 * @param set
	 * @throws IOException
	 */
	public static void searchFile(String path,Set<String> set) throws IOException {

		File f = new File(path);
		if (f.isDirectory()) {
			File[] fList = f.listFiles();

			for (File file : fList) {
				if (file.isDirectory()) {
					searchFile(file.getPath(),set);
				} else {
//					System.out.println((++i)+"  :"+file.getCanonicalPath());
					if (set.contains(file.getAbsolutePath())) {
						System.out.println((++i)+"  :"+file.getAbsolutePath());
						copyFile(file, new File("D:/test/springside4-jar/"+file.getName()));
					}
				}
			}
		}
	}
}

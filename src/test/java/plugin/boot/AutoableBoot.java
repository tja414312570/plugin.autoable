package plugin.boot;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

import org.apache.catalina.LifecycleException;

import com.yanan.autoable.ImageCompare;
import com.yanan.autoable.Tasker;
import com.yanan.framework.boot.PluginBoot;
import com.yanan.framework.boot.PluginBootServer;
import com.yanan.framework.boot.StandEnvironmentBoot;
import com.yanan.framework.plugin.PlugsFactory;
@PluginBoot(enviromentBoot = StandEnvironmentBoot.class,contextClass= {AutoableBoot.class,ImageCompare.class})
public class AutoableBoot {
	public static void main(String[] args) throws LifecycleException, IOException {
			PluginBootServer.run();
			Tasker tasker = PlugsFactory.getPluginsInstance(Tasker.class);
			tasker.start();
			ImageCompare imageCompare = PlugsFactory.getPluginsInstance(ImageCompare.class);
			System.out.println(imageCompare);
//			System.out.println(imageCompare.compare(null, null));
			File file1 = new File("/Users/yanan/Desktop/autoable/1.png");
			File file2 = new File("/Users/yanan/Desktop/autoable/2.png");
			InputStream inputStream1 = new FileInputStream(file2);
			ByteArrayOutputStream bais = new ByteArrayOutputStream();
			int ret = -1;
			while((ret = inputStream1.read()) != -1) {
				bais.write(ret);
			}
			inputStream1.close();
			byte[] bytes = bais.toByteArray();
			imageCompare.compare(bytes, null);
	}
}
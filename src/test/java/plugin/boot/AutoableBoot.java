package plugin.boot;

import java.io.UnsupportedEncodingException;

import org.apache.catalina.LifecycleException;

import com.yanan.framework.boot.PluginBoot;
import com.yanan.framework.boot.PluginBootServer;
import com.yanan.framework.boot.StandEnvironmentBoot;
@PluginBoot(enviromentBoot = StandEnvironmentBoot.class)
public class AutoableBoot {
	public static void main(String[] args) throws LifecycleException, UnsupportedEncodingException {
			PluginBootServer.run();
	}
}
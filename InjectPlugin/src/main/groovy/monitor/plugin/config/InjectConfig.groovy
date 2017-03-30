package monitor.plugin.config;

import org.gradle.api.Action;
import org.gradle.internal.hash.HashUtil;

import java.util.ArrayList;
import java.util.List;

import monitor.plugin.utils.ReflectUtils;

/**
 * Created by ZhouKeWen on 17/3/29.
 */
public class InjectConfig {
    boolean releaseEnabled = false;
    boolean debugEnabled = true;
    List<String> includePackages = new ArrayList<>();
    List<String> excludePackages = new ArrayList<>();
    List<String> excludeClasses = new ArrayList<>();

    Scope scope = new Scope();

    void setReleaseEnabled(boolean enabled) {
        releaseEnabled = enabled;
    }

    void setDebugEnabled(boolean enabled) {
        debugEnabled = enabled;
    }

    void setIncludePackages(List<String> includePackages) {
        this.includePackages.addAll(includePackages);
    }

    void setExcludePackages(List<String> excludePackages) {
        this.excludePackages.addAll(excludePackages);
    }

    void setExcludeClasses(List<String> excludeClasses) {
        this.excludeClasses.addAll(excludeClasses);
    }

    void scope(Action<Scope> action) {
        action.execute(scope);
    }

    Scope getScope() {
        return scope;
    }

    String generateHash() {
        String content = "releaseEnabled:{$releaseEnabled} debugEnabled:{$debugEnabled} includePackages:{$includePackages} excludePackages:{$excludePackages}" +
                " excludeClasses:{$excludeClasses} scope:" + ReflectUtils.printObject(scope);
        return HashUtil.createCompactMD5(content)
    }
}

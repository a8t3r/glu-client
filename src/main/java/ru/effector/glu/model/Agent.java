package ru.effector.glu.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Agent {

    @JsonProperty("glu.agent.apps")
    public String apps;

    @JsonProperty("glu.agent.commands.filesystem.dir")
    public String commandsFilesystemDir;

    @JsonProperty("glu.agent.commands.storageType")
    public String commandsStorageType;

    @JsonProperty("glu.agent.configURL")
    public String configURL;

    @JsonProperty("glu.agent.dataDir")
    public String dataDir;

    @JsonProperty("glu.agent.fabric")
    public String fabric;

    @JsonProperty("glu.agent.features.commands.enabled")
    public Boolean featuresCommandsEnabled;

    @JsonProperty("glu.agent.homeDir")
    public String homeDir;

    @JsonProperty("glu.agent.hostname")
    public String hostname;

    @JsonProperty("glu.agent.keystoreChecksum")
    public String keystoreChecksum;

    @JsonProperty("glu.agent.keystorePath")
    public String keystorePath;

    @JsonProperty("glu.agent.logDir")
    public String logDir;

    @JsonProperty("glu.agent.name")
    public String name;

    @JsonProperty("glu.agent.persistent.properties")
    public String persistentProperties;

    @JsonProperty("glu.agent.pid")
    public Integer pid;

    @JsonProperty("glu.agent.rest.nonSecure.port")
    public Integer nonSecurePort;

    @JsonProperty("glu.agent.rest.server.defaultThreads")
    public Integer restServerDefaultThreads;

    @JsonProperty("glu.agent.port")
    public Integer port;

    @JsonProperty("glu.agent.scriptRootDir")
    public String scriptRootDir;

    @JsonProperty("glu.agent.scriptStateDir")
    public String scriptStateDir;

    @JsonProperty("glu.agent.hostnameFactory")
    public String hostnameFactory;

    @JsonProperty("glu.agent.scripts.sharedClassLoader")
    public Boolean sharedClassLoader;

    @JsonProperty("glu.agent.sslEnabled")
    public Boolean sslEnabled;

    @JsonProperty("glu.agent.tempDir")
    public String tempDir;

    @JsonProperty("glu.agent.truststoreChecksum")
    public String truststoreChecksum;

    @JsonProperty("glu.agent.truststorePath")
    public String truststorePath;

    @JsonProperty("glu.agent.version")
    public String version;

    @JsonProperty("glu.agent.zkConnectString")
    public String zkConnectString;

    @JsonProperty("glu.agent.zkProperties")
    public String zkProperties;

    @JsonProperty("glu.agent.zkSessionTimeout")
    public String zkSessionTimeout;

    @JsonProperty("glu.agent.zookeeper.root")
    public String zookeeperRoot;

    @JsonProperty("viewURL")
    public String viewURL;

    @Override
    public String toString() {
        return "Agent" + "{fabric='" + fabric + '\'' + ", name='" + name + '\'' + ", pid=" + pid + '}';
    }
}
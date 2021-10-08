package com.clickhouse.client.config;

import com.clickhouse.client.ClickHouseChecker;

/**
 * System-wide default options. System properties and environment variables can
 * be set to change default value.
 * 
 * <p>
 * For example, by default {@link #ASYNC} is set to {@code true}. However, you
 * can change it to {@code false} by either specifying
 * {@code -Ddefault_async=false} on the Java command line, or setting
 * environment variable {@code DEFAULT_ASYNC=false}.
 */
public enum ClickHouseDefaults implements ClickHouseConfigOption {
    /**
     * Default execution mode.
     */
    ASYNC("async", true, "Whether the client should run in async mode."),
    /**
     * Whether to create session automatically when there are multiple queries.
     */
    AUTO_SESSION("auto_session", true, "Whether to create session automatically when there are multiple queries."),
    /**
     * Whether to resolve DNS name using
     * {@link com.clickhouse.client.ClickHouseDnsResolver}(e.g. resolve SRV record
     * to extract both host and port from a given name).
     */
    DNS_RESOLVE("dns_resolve", false, "Whether to resolve DNS name."),
    /**
     * Default cluster.
     */
    CLUSTER("cluster", "", "Cluster name."),
    /**
     * Default server host.
     */
    HOST("host", "localhost", "Host to connect to."),
    /**
     * Default protocol.
     */
    PROTOCOL("protocol", "ANY", "Protocol to use."),
    /**
     * Default server port.
     */
    PORT("port", 8123, "Port to connect to."),
    /**
     * Default server weight.
     */
    WEIGHT("weight", 1, "Server weight which might be used for load balancing."),
    /**
     * Default database.
     */
    DATABASE("database", "default", "Database to connect to."),
    /**
     * Default user.
     */
    USER("user", "default", "User name for authentication."),
    /**
     * Default password.
     */
    PASSWORD("password", "", "Password for authentication."),
    /**
     * Default compression.
     */
    COMPRESSION("compression", "LZ4", "Preferred compression alogrithm used in data transferring."),
    /**
     * Default format.
     */
    FORMAT("format", "TabSeparated", "Preferred data format for serialization and deserialization."),
    /**
     * Max threads.
     */
    MAX_THREADS("max_threads", 0, "Maximum size of shared thread pool, 0 or negative number means same as CPU cores."),
    /**
     * Max requests.
     */
    MAX_REQUESTS("max_requests", 0, "Maximum size of shared thread pool, 0 means no limit.");

    private final String key;
    private final Object defaultValue;
    private final Class<?> clazz;
    private final String description;

    <T> ClickHouseDefaults(String key, T defaultValue, String description) {
        this.key = ClickHouseChecker.nonNull(key, "key");
        this.defaultValue = ClickHouseChecker.nonNull(defaultValue, "defaultValue");
        this.clazz = defaultValue.getClass();
        this.description = ClickHouseChecker.nonNull(description, "description");
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getPrefix() {
        return "DEFAULT";
    }

    @Override
    public Class<?> getValueType() {
        return clazz;
    }
}
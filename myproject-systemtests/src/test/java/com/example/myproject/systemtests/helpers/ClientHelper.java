package com.example.myproject.systemtests.helpers;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.logging.LoggingFeature.Verbosity;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public final class ClientHelper {

    private static final Integer LOG_MAX_ENTITY_SIZE = 200;
    private static final TrustManager[] TRUST_ALL_CERTS_TRUSTMANAGERS = new TrustManager[]{new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }};

    public static ClientBuilder newClientBuilder(final boolean ignoreSSL) throws NoSuchAlgorithmException, KeyManagementException {
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, TRUST_ALL_CERTS_TRUSTMANAGERS, new SecureRandom());

        final ClientBuilder builder = ClientBuilder.newBuilder()
                .sslContext(sslContext)
                .hostnameVerifier((s1, s2) -> true);

        return builder;
    }

    public static Client newClient(final boolean ignoreSSL) throws NoSuchAlgorithmException, KeyManagementException {
        return newClient(defaultConfig(), ignoreSSL);
    }
    
    public static Client newClient(final ClientConfig config, final boolean ignoreSSL) throws NoSuchAlgorithmException, KeyManagementException {
        final ClientBuilder builder = newClientBuilder(ignoreSSL);
        final Client client = builder.withConfig(config).build();
        return client;
    }
    
    public static ClientConfig defaultConfig() {
        final ClientConfig config = new ClientConfig();
        config.register(new LoggingFeature(Logger.getLogger(ClientHelper.class.getName()), Level.INFO, Verbosity.PAYLOAD_TEXT, LOG_MAX_ENTITY_SIZE));
        return config;
    }

    private ClientHelper() {
    }

}

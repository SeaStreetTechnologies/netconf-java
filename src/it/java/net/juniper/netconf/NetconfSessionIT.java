package net.juniper.netconf;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Category(IntegrationTest.class)
public class NetconfSessionIT {

    @Test //Expectation is no exception thrown.
    public void testNetconfSessionWithKey() throws Exception {
        try (Device device = Device.builder()
                .hostName("localhost")
                .userName(System.getProperty("user.name"))
                .keyBasedAuthentication(true)
                .pemKeyFile("/home/vagrant/.ssh/netconf_rsa")
                .strictHostKeyChecking(false)
                .build()) {
            device.connect();
            XML xml = device.getRunningConfig();
            assertThat(xml).isNotNull();
            assertThat(String.valueOf(xml).trim()).isNotBlank();
            log.debug("XML:\n{}", xml);
        }
    }
}

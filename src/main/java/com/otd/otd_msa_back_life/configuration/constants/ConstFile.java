package com.otd.otd_msa_back_life.configuration.constants;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "constants.file")
@RequiredArgsConstructor
@ToString
public class ConstFile {
    public final String directory;

}

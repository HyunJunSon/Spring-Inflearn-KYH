package kimyh.typeconverter.converter;

import kimyh.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {
    @Override
    public IpPort convert(String source) {
        log.info("convert source={}", source);
        String[] token = source.split(":");
        String ip = token[0];
        Integer port = Integer.valueOf(token[1]);
        return new IpPort(ip, port);
    }
}

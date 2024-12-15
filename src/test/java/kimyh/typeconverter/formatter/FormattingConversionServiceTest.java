package kimyh.typeconverter.formatter;

import kimyh.typeconverter.converter.IpPortToStringConverter;
import kimyh.typeconverter.converter.StringToIpPortConverter;
import kimyh.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService() {

        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        conversionService.addFormatter(new MyNumberFormatter());  // 컨버터, 포매터 둘다 등록 가능

        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));
        assertThat(ipPort).isInstanceOf(IpPort.class);

        String result1 = conversionService.convert(1000L, String.class);
        assertThat(result1).isEqualTo("1,000");

        Long result2 = conversionService.convert("1,000L", Long.class);
        assertThat(result2).isEqualTo(1000L);

    }
}

package kimyh.typeconverter;

import kimyh.typeconverter.converter.IntegerToStringConverter;
import kimyh.typeconverter.converter.IpPortToStringConverter;
import kimyh.typeconverter.converter.StringToIntegerConverter;
import kimyh.typeconverter.converter.StringToIpPortConverter;
import kimyh.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());
        registry.addFormatter(new MyNumberFormatter());
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
    }
}

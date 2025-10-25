package com.rubai.rubai_master.configurations

import com.rubai.rubai_master.utils.MdcInterceptorUtils
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component
class WebMvcConfig (private val mdcInterceptor: MdcInterceptorUtils) : WebMvcConfigurer
{
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(mdcInterceptor)
    }

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val jsonConverter = MappingJackson2HttpMessageConverter()
        jsonConverter.supportedMediaTypes = listOf(
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_OCTET_STREAM
        )
        converters.add(jsonConverter)
    }

}


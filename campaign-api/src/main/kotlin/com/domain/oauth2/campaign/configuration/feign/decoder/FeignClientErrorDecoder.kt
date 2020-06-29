package com.domain.oauth2.campaign.configuration.feign.decoder

import feign.Response
import feign.codec.ErrorDecoder

import feign.FeignException.errorStatus
import org.apache.commons.io.IOUtils
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class FeignClientErrorDecoder : ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {
        val errorMessage = IOUtils.toString(response.body().asInputStream())
        return when {
//            HttpStatus.valueOf(response.status()) == HttpStatus.UNAUTHORIZED ->
//                FeignAuthenticationException(ErrorResponse(response.status(), errorMessage).toJson(), response)
//            HttpStatus.valueOf(response.status()) == HttpStatus.NOT_FOUND ->
//                FeignNotFoundException(ErrorResponse(response.status(), errorMessage).toJson(), response)
//            HttpStatus.valueOf(response.status()) == HttpStatus.REQUEST_TIMEOUT || HttpStatus.valueOf(response.status()) == HttpStatus.GATEWAY_TIMEOUT ->
//                FeignTimeOutException(ErrorResponse(response.status(), errorMessage).toJson(), response)
//            HttpStatus.valueOf(response.status()).is4xxClientError ->
//                FeignClientException(ErrorResponse(response.status(), errorMessage).toJson(), response)
//            HttpStatus.valueOf(response.status()).is5xxServerError ->
//                FeignServerException(errorMessage, response)
            else -> errorStatus(methodKey, response)
        }
    }
}

package com.books.app.validator;

import com.books.app.model.BookDto;
import com.books.app.exception.ApiError;
import com.books.app.exception.ApiException;
import com.books.app.exception.ReasonCode;
import com.books.app.util.MessagesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.books.app.util.ValidationUtils.rejectIfNullOrEmpty;

@Component
public class ReaderValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookValidator.class);

    public void validateReaderRequest(BookDto bookDto, Long id) throws ApiException {
        LOGGER.info("Validating request");
        if (rejectIfNullOrEmpty(bookDto)) {
            throw new ApiException(new ApiError("BookController", ReasonCode.INVALID_REQUEST_002.getCode(), MessagesUtil.getMessage("invalid.request"), HttpStatus.BAD_REQUEST));
        }
    }
}

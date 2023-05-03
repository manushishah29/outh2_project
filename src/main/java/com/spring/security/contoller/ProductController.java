package com.spring.security.contoller;


import com.spring.security.dto.RequestProductDto;
import com.spring.security.dto.ResponseProductDto;
import com.spring.security.enums.CustomEnums;
import com.spring.security.exception.CustomException;
import com.spring.security.response.ApiResponse;
import com.spring.security.service.CustomProductDetailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@PropertySource(value = "classpath:swagger-api-doc/swagger-product.properties")
@Slf4j

public class ProductController {
    @Autowired
    CustomProductDetailService customProductDetailService;

    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Product add Successfully ", examples = @Example(value =
                    {@ExampleProperty(value = "file:/swagger-api-doc/example/product.info.200.example.json",
                            mediaType = MediaType.APPLICATION_JSON_VALUE)})),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Invalid", examples = @Example(value =
                    {@ExampleProperty(value = "file:/swagger-api-doc/example/product.info.404.example.json",
                            mediaType = MediaType.APPLICATION_JSON_VALUE)})),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Missing value", examples = @Example(value =
                    {@ExampleProperty(value = "file:/swagger-api-doc/example/product.info.400.example.json",
                            mediaType = MediaType.APPLICATION_JSON_VALUE)})),

    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "This api will be used to add Product", notes = "${product.info.notes}")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> addProduct(@RequestBody @Valid RequestProductDto requestProductDto) {
        try {
            ResponseProductDto responseProductDto = this.customProductDetailService.add(requestProductDto);
            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Product Added Successfully", responseProductDto),
                    HttpStatus.OK);
        } catch (CustomException e) {
            log.error("error ::{}", e);
            throw e;
        } catch (Exception e) {
            throw new CustomException(CustomEnums.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Product Update Successfully ", examples = @Example(value =
                    {@ExampleProperty(value = "file:/swagger-api-doc/example/product.info.200.example.json",
                            mediaType = MediaType.APPLICATION_JSON_VALUE)})),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Invalid", examples = @Example(value =
                    {@ExampleProperty(value = "file:/swagger-api-doc/example/product.info.404.example.json",
                            mediaType = MediaType.APPLICATION_JSON_VALUE)})),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Missing value", examples = @Example(value =
                    {@ExampleProperty(value = "file:/swagger-api-doc/example/product.info.400.example.json",
                            mediaType = MediaType.APPLICATION_JSON_VALUE)})),

    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "This api will be used to Update Product", notes = "${product.info.notes}")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> Update(@RequestBody @Valid RequestProductDto requestProductDto, @PathVariable Long id) {
        try {

            ResponseProductDto responseProductDto = this.customProductDetailService.update(requestProductDto, id);
            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Product updated Successfully", responseProductDto),
                    HttpStatus.OK);
        } catch (CustomException e) {
            log.error("error ::{}", e);
            throw e;
        } catch (Exception e) {
            throw new CustomException(CustomEnums.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Product Fetched Successfully ", examples = @Example(value =
                    {@ExampleProperty(value = "file:/swagger-api-doc/example/product.info.200.example.json",
                            mediaType = MediaType.APPLICATION_JSON_VALUE)})),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Invalid", examples = @Example(value =
                    {@ExampleProperty(value = "file:/swagger-api-doc/example/product.info.404.example.json",
                            mediaType = MediaType.APPLICATION_JSON_VALUE)})),
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @ApiOperation(value = "This api will be used to display Products", notes = "${product.getAll.notes}")
    @GetMapping(value = "/display", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<ApiResponse> display(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                               @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue,
                                               @RequestParam(value = "sortBy", required = false, defaultValue = "productname") String sortBy,
                                               @RequestParam(value = "sortAs", required = false, defaultValue = "ASC") Sort.Direction sortAs ){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortAs, sortBy));
        Page<ResponseProductDto> ls = this.customProductDetailService.getAll( pageable);
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Product Fetched Successfully", ls), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Product fetched id Successfully ", examples = @Example(value =
                    {@ExampleProperty(value = "file:/swagger-api-doc/example/user.info.200.example.json",
                            mediaType = MediaType.APPLICATION_JSON_VALUE)})),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Invalid", examples = @Example(value =
                    {@ExampleProperty(value = "file:/swagger-api-doc/example/user.info.404.example.json",
                            mediaType = MediaType.APPLICATION_JSON_VALUE)})),

    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "This api will be used to fetched id in Products", notes = "${product.byProductId.notes}")
    @GetMapping(value = "/findById", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity findById(@RequestParam long id) {
        ResponseProductDto responseProductDto = this.customProductDetailService.searchById(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Product Fetched id Successfully", responseProductDto), HttpStatus.OK);
    }


    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Product Delete Successfully ", examples = @Example(value =
                    {@ExampleProperty(value = "file:/swagger-api-doc/example/product.info.200.example.json",
                            mediaType = MediaType.APPLICATION_JSON_VALUE)})),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Invalid", examples = @Example(value =
                    {@ExampleProperty(value = "file:/swagger-api-doc/example/product.info.404.example.json",
                            mediaType = MediaType.APPLICATION_JSON_VALUE)})),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "This api will be used to delete Product", notes = "${product.byProductId.notes}")
    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> delete(@RequestParam long id) {
        this.customProductDetailService.delete(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Product Deleted successfully"), HttpStatus.OK);
    }

}

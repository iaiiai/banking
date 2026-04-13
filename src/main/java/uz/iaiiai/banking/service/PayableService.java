package uz.iaiiai.banking.service;

import uz.iaiiai.banking.dto.request.PayableRequestDto;
import uz.iaiiai.banking.dto.request.PayableStatusUpdateRequestDto;
import uz.iaiiai.banking.dto.response.PayableResponseDto;
import uz.iaiiai.banking.dto.response.PayableStatusUpdateResponseDto;

import java.util.List;

public interface PayableService {
    PayableResponseDto createPayableAsAdmin(PayableRequestDto dto);
    PayableStatusUpdateResponseDto setPayableStatusAsAdmin(String alias, PayableStatusUpdateRequestDto dto);
    PayableResponseDto getPayable(String alias);
    List<PayableResponseDto> getPayables(int page, int size);
    List<PayableResponseDto> getPayablesAsAdmin(int page, int size);
}

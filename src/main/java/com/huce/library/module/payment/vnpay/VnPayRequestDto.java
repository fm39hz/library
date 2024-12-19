package com.huce.library.module.payment.vnpay;

import com.huce.library.configuration.VnPayConfig;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.HmacUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
public class VnPayRequestDto {
    private final VnPayConfig config;
    private final Long id;
    private final Float amount;
    private final String paymentInfo;

    @Override
    public String toString() {
        Map<String, Object> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", config.getVersion());
        vnp_Params.put("vnp_Command", config.getCommand());
        vnp_Params.put("vnp_TmnCode", config.getTmnCode());
        vnp_Params.put("vnp_Amount", (int) (amount * 100));
        vnp_Params.put("vnp_CurrCode", config.getCurrCode());
        vnp_Params.put("vnp_TxnRef", String.valueOf(id));
        vnp_Params.put("vnp_OrderInfo", paymentInfo);
        vnp_Params.put("vnp_OrderType", config.getOrderType());
        vnp_Params.put("vnp_Locale", config.getLocale());
        vnp_Params.put("vnp_ReturnUrl", config.getReturnUrl());
        vnp_Params.put("vnp_IpAddr", config.getIpAddr());
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnpCreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", formatter.format(formatter.parse(vnpCreateDate)));
            cld.add(Calendar.MINUTE, 15);
            String vnpExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", formatter.format(formatter.parse(vnpExpireDate)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName).toString();
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String secureHash = new HmacUtils("hmacSHA512", config.getHashSecret()).hmacHex(hashData.toString());
        queryUrl += "&vnp_SecureHash=" + secureHash;
        return config.getUrl() + "?" + queryUrl;
    }
}

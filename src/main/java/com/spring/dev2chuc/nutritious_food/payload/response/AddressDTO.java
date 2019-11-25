package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.helper.DateTimeHelper;
import com.spring.dev2chuc.nutritious_food.model.Address;
import com.spring.dev2chuc.nutritious_food.model.Order;
import com.spring.dev2chuc.nutritious_food.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class AddressDTO {
    private Long id;
    private String title;
    private UserDTO user;
    private Integer status;
    private String createdAt;
    private String updatedAt;

    public AddressDTO(Address address, boolean hasUser) {
        this.id = address.getId();
        this.title = address.getTitle();
        if (hasUser) this.user = new UserDTO(address.getUser(), false, false, false,false, false);
        this.status = address.getStatus();
        this.createdAt = DateTimeHelper.formatDateFromLong(address.getCreatedAt());
        this.updatedAt = DateTimeHelper.formatDateFromLong(address.getUpdatedAt());
    }
}

package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.business.UpdateUserDetailsUseCase;
import com.fontys.s3.buyahouse.business.exceptions.InvalidUserException;
import com.fontys.s3.buyahouse.domain.UpdateUserDetailsRequest;
import com.fontys.s3.buyahouse.persistance.UserRepository;
import com.fontys.s3.buyahouse.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UpdateUserDetailsUseCaseImpl implements UpdateUserDetailsUseCase {
    private final UserRepository userRepository;
    @Override
    @Transactional
    public void updateUserDetails(UpdateUserDetailsRequest request) {
        UserEntity user = userRepository.findByUserId(request.getUserId());
        if(user == null || !userRepository.existsById(request.getUserId())){
            throw new InvalidUserException("USER_ID_INVALID");
        }
        else {
            updateFields(request, user);
        }
    }

    public void updateFields(UpdateUserDetailsRequest request, UserEntity user){
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setUsername(request.getUsername());
        userRepository.save(user);
    }
}

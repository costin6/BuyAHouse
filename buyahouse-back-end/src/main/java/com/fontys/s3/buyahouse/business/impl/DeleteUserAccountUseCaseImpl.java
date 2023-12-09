package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.business.DeleteUserAccountUseCase;
import com.fontys.s3.buyahouse.business.exceptions.InvalidUserException;
import com.fontys.s3.buyahouse.persistance.PropertyRepository;
import com.fontys.s3.buyahouse.persistance.UserRepository;
import com.fontys.s3.buyahouse.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class DeleteUserAccountUseCaseImpl implements DeleteUserAccountUseCase {
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    @Override
    @Transactional
    public void deleteUserAccount(Long userId) {
        UserEntity user = userRepository.findByUserId(userId);
        if(user != null){
            propertyRepository.deleteByUser(user);
            userRepository.deleteById(userId);
        }
        else{
            throw new InvalidUserException();
        }
    }
}

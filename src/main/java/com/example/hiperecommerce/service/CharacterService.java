package com.example.hiperecommerce.service;

import com.example.hiperecommerce.entity.Character;
import com.example.hiperecommerce.reposiory.CharacterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class CharacterService{

    private CharacterRepository characterRepository;

    public Character get(Long userId){
        return characterRepository.findAll().stream().filter(x->x.getUser().getId()==userId).findFirst().get();
    }

    public Character update(Character character) throws InterruptedException {
        var c = characterRepository.findById(character.getId()).get();
        c.setName(character.getName());
        c.setSecondname(character.getSecondname());
        c.setSurname(character.getSurname());
        c.setBirtdate(character.getBirtdate());
        c.setGender(character.getGender());
        c.setMobilenumber(character.getMobilenumber());
        c.setHomenumber(character.getHomenumber());
        c.setOfficephonenumber(character.getOfficephonenumber());
        Thread.sleep(2000);
        return characterRepository.findById(character.getId()).get();
    }

}

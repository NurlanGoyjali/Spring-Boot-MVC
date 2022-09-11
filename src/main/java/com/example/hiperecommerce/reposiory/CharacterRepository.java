package com.example.hiperecommerce.reposiory;

import com.example.hiperecommerce.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Long> {
}

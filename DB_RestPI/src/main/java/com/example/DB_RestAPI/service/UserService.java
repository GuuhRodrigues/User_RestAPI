package com.example.DB_RestAPI.service;
import com.example.DB_RestAPI.repository.UserRepository;
import com.example.DB_RestAPI.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> obterTodos() {
        return userRepository.findAll();
    }

    public UserEntity obterPorId(String id) {
        return userRepository.findById(id).orElse(null);
    }

//    public UserEntity inserir(UserEntity user) {
//        return userRepository.save(user);
//    }

    public void inserirList(List<UserEntity> users) {
        for (UserEntity user: users) {
            userRepository.save(user);
        }
    }

    // Métodos utilizando consultas personalizadas
    public List<UserEntity> buscarPorNome(String nome) {
        return userRepository.findByNomeIgnoreCase(nome);
    }

//    public List<UserEntity> buscarPorEmail(String email) {
//        return userRepository.findByEmailIgnoreCase(email);
//    }
//
//    public List<UserEntity> buscarPorNomeEEmail(String nome, String email) {
//        return userRepository.findByNomeAndEmailAllIgnoreCase(nome, email);
//    }

    public List<UserEntity> buscarPorNomeQueComecaCom(String prefixo) {
        return userRepository.findByNomeStartingWithIgnoreCase(prefixo);
    }

    public List<UserEntity> buscarPorNomeQueContem(String substring) {
        return userRepository.findByNomeContainingIgnoreCase(substring);
    }

    public UserEntity atualizar(String id, UserEntity newUser) {
        UserEntity existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setEmail(newUser.getEmail());
            existingUser.setNome(newUser.getNome());
            return userRepository.save(existingUser);
        } else {
            // Se o usuário não existe:
            return null;
            // Podemos também lançar uma exceção:
            // throw new EntityNotFoundException("Usuário com id " + id + " não encontrado");
        }
    }

    public void excluir(String id) {
        userRepository.deleteById(id);
    }
}
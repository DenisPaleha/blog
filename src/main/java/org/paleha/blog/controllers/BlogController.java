package org.paleha.blog.controllers;

//import org.paleha.blog.models.Post;
//import org.paleha.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Аннотация - контроллер
public class BlogController {

//    @Autowired
//    private PostRepository postRepository;

    @GetMapping("/blog") // Аннотация определяет какую именно страницу отслеживает контроллер
    public String blogMine(Model model) { // Функция возвращает строку с названием шаблона, который мы должны подключить
//        Iterable<Post> posts = postRepository.findAll();
//        model.addAttribute("posts", posts); // Передаем данные по имени "posts" из объекта Iterable<Post>
        return "info";
    }

}

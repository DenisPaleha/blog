package org.paleha.blog.controllers;

import org.paleha.blog.main.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final Main main;


    // Внедрение зависимости (dependency injection) через конструктор
    @Autowired // Аннотация добавляет зависимость Spring и позволяет
    // создать экземпляр класса (с аннотацией @Component или @Service)
    public MainController(Main main) {
        this.main = main;
    }

    @GetMapping("/") // косая черта в кавычках означает что функция обрабатывает корневую или главную страницу
    public String home(Model model) {
        model.addAttribute("title", "Главная страница"); // Передаем параметры, которые будут применены к шаблону
        return "home"; // возвращает шаблон домашней страницы
    }

    @PostMapping("/calculatorForm")
    public String calculatorForm(String userInput, Model model) throws Exception {
        // userInput содержит данные, введенные пользователем.
        // Выполняем обработку данных, например, преобразуем строку.
        String calculatorOutput = main.calculator(userInput);
        // Добавляем обработанные данные в модель для возврата на страницу
        model.addAttribute("calculatorOutput", calculatorOutput);
        return "home"; // возвращает шаблон домашней страницы (где находится ваша форма)
    }


}
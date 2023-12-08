package org.paleha.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

//    private final BinaryDecoder binaryDecoder;
//
//    // Внедрение зависимости через конструктор
//    public MainController(BinaryDecoder binaryDecoder) {
//        this.binaryDecoder = binaryDecoder;
//    }

    @GetMapping("/") // косая черта в кавычках означает что функция обрабатывает корневую или главную страницу
    public String home(Model model) {
        model.addAttribute("title", "Главная страница"); // Передаем параметры, которые будут применены к шаблону
        return "home"; // возвращает шаблон домашней страницы
    }

//    @PostMapping("/processForm")
//    public String processForm(String userInput, Model model) throws Exception {
//        // userInput содержит данные, введенные пользователем.
//        // Выполняем обработку данных, например, преобразуем строку.
//        String processedInput = decoder(userInput);
//
//
//        // Добавляем обработанные данные в модель для возврата на страницу
//        model.addAttribute("processedInput", processedInput);
//
//        return "home"; // возвращает шаблон домашней страницы (где находится ваша форма)
//    }

    //    @PostMapping
//    public String processForm(String userInput) {
//        // userInput содержит данные, введенные пользователем
//        // Вы можете выполнить нужные действия с этими данными здесь
//        return "result"; // возвращает шаблон для отображения результата
//    }


}
package org.paleha.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static org.paleha.blog.decoders.DecoderToDecimal.decoder;

@Controller // Аннотация должна быть указана - иначе это просто класс!
public class ConverterController {

    @GetMapping("/converter") // Аннотация определяет какую именно страницу отслеживает контроллер
    public String converter(Model model) { // Функция возвращает строку с названием шаблона, который мы должны подключить
        model.addAttribute("title", "Конвертация в десятичные");
        return "converter";
    }

    @PostMapping("/processForm")
    public String processForm(String userInput, Model model) throws Exception {
        // userInput содержит данные, введенные пользователем.
        // Выполняем обработку данных, например, преобразуем строку.
        String convertToDecimal = decoder(userInput);
        // Добавляем обработанные данные в модель для возврата на страницу
        model.addAttribute("convertToDecimal", convertToDecimal);
        return "converter"; // возвращает шаблон домашней страницы (где находится ваша форма)
    }
}

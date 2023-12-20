package org.paleha.blog.controllers;

import org.paleha.blog.decoders.DecoderToDecimal;
import org.paleha.blog.exceptions.ConversionException;
import org.paleha.blog.exceptions.OutOfRangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Аннотация должна быть указана - иначе это просто класс!
public class ConverterController {

    private final DecoderToDecimal decoderToDecimal;
    private String UserInputToDecimal = ""; // Последнее введенное значение первого поля.
    private String lastSelectedRadio = "Rome"; // Переменная для хранения последнего выбранного значения радиокнопки
    private String lastUserInput = ""; // Переменная для хранения последнего введенного значения второго поля


    @Autowired // Аннотация добавляет зависимость Spring и позволяет
    // создать экземпляр класса (с аннотацией @Component или @Service)
    public ConverterController(DecoderToDecimal decoderToDecimal) {
        this.decoderToDecimal = decoderToDecimal;
    }

    @GetMapping("/converter") // Аннотация определяет какую именно страницу отслеживает контроллер
    public String converter(Model model) { // Функция возвращает строку с названием шаблона, который мы должны подключить
        model.addAttribute("title", "Конвертация в десятичные");
        model.addAttribute("UserInputToDecimal", UserInputToDecimal); // Передаем последнее введенное значение первого поля
        model.addAttribute("lastSelectedRadio", lastSelectedRadio); // Передаем последнее выбранное значение радиокнопки
        model.addAttribute("lastUserInput", lastUserInput); // Передаем последнее введенное значение второго поля
        return "converter";
    }

    /**
     * Обработка первой формы - конвертация в десятичное число
     */
    @PostMapping("/toDecimal")
    public String toDecimal(String userInput, Model model) throws Exception {
        // userInput содержит данные, введенные пользователем.
        // Выполняем обработку данных, например, преобразуем строку.
        UserInputToDecimal = userInput;
        try {
            String decimal = decoderToDecimal.decoder(userInput);
            model.addAttribute("convertToDecimal", decimal);
        } catch (ConversionException e) {
            model.addAttribute("convertToDecimal", e.getMessage());
        }
        // Добавляем обработанные данные в модель для возврата на страницу
//        model.addAttribute("convertToDecimal", decimal);
        model.addAttribute("UserInputToDecimal", UserInputToDecimal);
        return "converter"; // возвращает шаблон домашней страницы (где находится ваша форма)
    }

    /**
     * Обработка второй формы - конвертация из десятичного числа
     */
    @PostMapping("/fromDecimal")
    public String fromDecimal(@RequestParam("userInputDecimal") String userInputDecimal, @RequestParam("question") String userChoice, Model model) throws Exception {
        String result = "";
        lastSelectedRadio = userChoice;
        lastUserInput = userInputDecimal;
        try {
            double input = Integer.parseInt(userInputDecimal); // Парсим строку в integer
            // userInputDecimal -- это строка.
            // а если пользователь ввел не целое число - ошибка. Значит надо сделать проверку!


            // В зависимости от выбора пользователя выполняем нужное действие
            if (userChoice.equals("Rome")) { // Декодируем в Rome
                try {
                    result = decoderToDecimal.toRome(input);
                } catch (OutOfRangeException e) {
                    result = e.getMessage();
                    model.addAttribute("convertFromDecimal", result);
                }
            } else if (userChoice.equals("Binary")) { // Декодируем в Binary
                result = decoderToDecimal.toBinary(input);
            } else if (userChoice.equals("Oct")) { // Декодируем в Oct
                result = decoderToDecimal.toOct(input);
            } else if (userChoice.equals("Hex")) { // Декодируем в Hex
                result = decoderToDecimal.toHex(input);
            }


        } catch (Exception e) {
            result = "Введите целое число";
            model.addAttribute("convertFromDecimal", result);
        }
        model.addAttribute("convertFromDecimal", result);
        model.addAttribute("lastSelectedRadio", lastSelectedRadio); // Передаем последнее выбранное значение радиокнопки
        model.addAttribute("lastUserInput", lastUserInput); // Передаем последнее введенное значение
        // Добавляем обработанные данные в модель для возврата на страницу
//        model.addAttribute("convertFromDecimal", result);
        return "converter"; // возвращает шаблон домашней страницы (где находится ваша форма)
    }
}

package ru.Sokolov.MyFirstAppSpringBoot.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    private ArrayList<String> arrayList = new ArrayList<>();
    private HashMap<Integer, String> hashMap = new HashMap<>();
    private int mapCounter = 1;

    //http://localhost:8080/hello
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name",
            defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    //http://localhost:8080/update-array
    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam String s) {
            arrayList.add(s);
            return "Элемент добавлен в существующий ArrayList: " + s;
    }
    //http://localhost:8080/show-array
    @GetMapping("/show-array")
    public String showArrayList() {
        if (arrayList.isEmpty()) {
            return "ArrayList пуст";
        }
        return "Элементы ArrayList: " + arrayList.toString();
    }

    //http://localhost:8080/update-map
    @GetMapping("/update-map")
    public String updateHashMap(@RequestParam String s) {
            // Добавляем элемент в существующий HashMap
            hashMap.put(mapCounter, s);
            String result = "Элемент добавлен [Ключ: " + mapCounter + ", Значение: " + s + "].";
            mapCounter++;
            return result;
    }

    //http://localhost:8080/show-map
    @GetMapping("/show-map")
    public String showHashMap() {
        if (hashMap.isEmpty()) {
            return "HashMap пуст";
        }

        StringBuilder result = new StringBuilder("Элементы HashMap:\n");
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            result.append(String.format("Ключ: %d, Значение: '%s'\n", entry.getKey(), entry.getValue()));
        }
        return result.toString();
    }

    //http://localhost:8080/show-all-length
    @GetMapping("/show-all-length")
    public String showAllLength() {
        return String.format("Количество элементов в ArrayList: %d\nКоличество элементов в HashMap: %d",
                arrayList.size(), hashMap.size());
    }
}

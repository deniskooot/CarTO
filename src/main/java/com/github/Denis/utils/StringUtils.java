package com.github.Denis.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class StringUtils {

  private static final Map<String, String> ABBREVIATIONS_MAP = createAbbreviationsMap();

  private static Map<String, String> createAbbreviationsMap() {
    Set<String> abbreviations =
        Set.of(
            "ГРМ", // Газораспределительный механизм (Timing Belt/Chain)
            "ABS", // Антиблокировочная система (Anti-lock Braking System)
            "ESP", // Электронная система стабилизации (Electronic Stability Program)
            "АКПП", // Автоматическая коробка переключения передач (Automatic Transmission)
            "МКПП", // Механическая коробка переключения передач (Manual Transmission)
            "ТНВД", // Топливный насос высокого давления (High-Pressure Fuel Pump)
            "EGR", // Система рециркуляции отработанных газов (Exhaust Gas Recirculation)
            "DPF", // Сажевый фильтр (Diesel Particulate Filter)
            "ТКР", // Турбокомпрессор (Turbocharger)
            "SRS", // Система пассивной безопасности (Supplemental Restraint System)
            "VVT", // Система изменения фаз газораспределения (Variable Valve Timing)
            "ECU", // Электронный блок управления (Engine Control Unit)
            "ОЖ", // Охлаждающая жидкость (Coolant)
            "ГУР", // Гидроусилитель руля (Hydraulic Power Steering)
            "ЭУР", // Электроусилитель руля (Electric Power Steering)
            "ТВС", // Топливная система (Fuel System)
            "РХХ", // Регулятор холостого хода (Idle Air Control)
            "ДМРВ", // Датчик массового расхода воздуха (Mass Air Flow Sensor)
            "КПП", // Коробка переключения передач (Transmission)
            "ДВС", // Двигатель внутреннего сгорания (Internal Combustion Engine)
            "ТО", // Техническое обслуживание (Technical Maintenance)
            "ЛКП", // Лакокрасочное покрытие (Paintwork)
            "ПТФ", // Противотуманные фары (Fog Lights)
            "ДПКВ", // Датчик положения коленвала (Crankshaft Position Sensor)
            "ДПДЗ", // Датчик положения дроссельной заслонки (Throttle Position Sensor)
            "АКБ", // Аккумуляторная батарея (Battery)
            "ГБЦ", // Головка блока цилиндров (Cylinder Head)
            "ТЭН", // Топливный электронагреватель (Fuel Heater)
            "БЦ", // Блок цилиндров (Cylinder Block)
            "КВ", // Коленчатый вал (Crankshaft)
            "ВКГ", // Вентиляция картерных газов (Crankcase Ventilation)
            "КХХ", // Клапан холостого хода (Idle Control Valve)
            "ДТ", // Дизельное топливо (Diesel Fuel)
            "МАФ", // Массовый расход воздуха (Mass Air Flow)
            "ИМ", // Исполнительный механизм (Actuator)
            "КД", // Компрессионное давление (Compression Pressure)
            "НВД", // Насос высокого давления (High Pressure Pump)
            "ПВ", // Приводной вал (Drive Shaft)
            "РК", // Раздаточная коробка (Transfer Case)
            "СТ", // Система тормозов (Brake System)
            "ТН", // Топливный насос (Fuel Pump)
            "ФН", // Форсунка (Injector)
            "ШПГ", // Шатунно-поршневая группа (Piston-Rod Group)
            "ЩУ", // Щиток управления (Control Panel)
            "ЭБУ", // Электронный блок управления (Electronic Control Unit)
            "ЯМЗ", // Ярославский моторный завод (Engine Brand)
            "ЗМЗ", // Заволжский моторный завод (Engine Brand)
            "КМ", // Клапанная механизм (Valve Mechanism)
            "Лямбда", // Лямбда-зонд (Oxygen Sensor)
            "ОГ", // Отработанные газы (Exhaust Gases)
            "ПН", // Подвеска независимая (Independent Suspension)
            "РД", // Регулятор давления (Pressure Regulator)
            "СН", // Система навигации (Navigation System)
            "ТД", // Турбодизель (Turbo Diesel)
            "УОЗ", // Угол опережения зажигания (Ignition Timing Angle)
            "ФВ", // Фильтр воздушный (Air Filter)
            "ХХ", // Холостой ход (Idle Speed)
            "ЦПГ", // Цилиндро-поршневая группа (Cylinder-Piston Group)
            "ЧТ", // Частота топливоподачи (Fuel Delivery Rate)
            "ШР", // Шаровой рычаг (Ball Joint)
            "ЭМК", // Электромагнитный клапан (Solenoid Valve)
            "ЯЗ", // Ярославский завод (Component Brand)
            "ВВ", // Высоковольтные провода (High-Voltage Wires)
            "ГТЦ", // Главный тормозной цилиндр (Master Brake Cylinder)
            "ДТОЖ", // Датчик температуры охлаждающей жидкости (Coolant Temperature Sensor)
            "ЗДТ", // Задний дифференциал (Rear Differential)
            "КЛ", // Клапан (Valve)
            "ММ", // Масляный модуль (Oil Module)
            "НД", // Насос давления (Pressure Pump)
            "ПД", // Передний дифференциал (Front Differential)
            "РТ", // Рабочая температура (Operating Temperature)
            "СВ", // Свечи зажигания (Spark Plugs)
            "ТВ", // Топливный бак (Fuel Tank)
            "УД", // Устройство диагностики (Diagnostic Tool)
            "ФД", // Фильтр двигателя (Engine Filter)
            "ХД", // Ходовая часть (Chassis)
            "ЦД", // Центральный дифференциал (Central Differential)
            "ШД", // Шаговый двигатель (Stepper Motor)
            "ЭД", // Электродвигатель (Electric Motor)
            "ЯД", // Якорь двигателя (Engine Armature)
            "БК", // Бортовой компьютер (On-Board Computer)
            "ВК", // Впускной клапан (Intake Valve)
            "ГК", // Генератор (Alternator)
            "ДК", // Датчик кислорода (Oxygen Sensor)
            "ЕК", // Емкость конденсатора (Capacitor Capacity)
            "ЖК", // Жидкостный клапан (Fluid Valve)
            "ЗК", // Задний клапан (Rear Valve)
            "ИК", // Инжекторный клапан (Injector Valve)
            "ЛК", // Левый клапан (Left Valve)
            "МК", // Масляный клапан (Oil Valve)
            "НК", // Насосный клапан (Pump Valve)
            "ОК", // Охлаждающий клапан (Cooling Valve)
            "ПК", // Правый клапан (Right Valve)
            "РКП", // Ремкомплект подвески (Suspension Repair Kit)
            "СК", // Сцепление (Clutch)
            "ТК", // Топливный клапан (Fuel Valve)
            "УК", // Управляющий клапан (Control Valve)
            "ФК", // Фильтрующий клапан (Filter Valve)
            "ХК", // Холодный клапан (Cold Valve)
            "ЧК" // Четырехканальный клапан (Four-Channel Valve)
            );
    Map<String, String> map = new HashMap<>();
    // приводим все аббревиатуры в нижний и верхний регистр, нижний регистр для сравнения с
    // обрабатываемой строкой
    for (String abbr : abbreviations) {
      map.put(abbr.toLowerCase(), abbr.toUpperCase());
    }
    return map;
  }

  private StringUtils() {}

  public static String normalizeServiceScheduleName(String input) {
    if (input == null || input.isEmpty()) return input;

    /* Разбиваем строку на слова
    экранирует обратный слеш в строке Java.
    \s в регулярном выражении означает любой пробельный символ (пробел, табуляция и т.д.).
    + означает "один или более".
    */
    String[] words = input.split("\\s+");
    StringJoiner result = new StringJoiner(" ");
    for (String word : words) {
      result.add(normalizeWord(word));
    }
    String finalResult = result.toString();
    return finalResult.isEmpty()
        ? finalResult
        :
        // Первый символ названия работы приводим в верхний регистр
        Character.toUpperCase(finalResult.charAt(0)) + finalResult.substring(1);
  }

  //  Если аббревиатура есть в списке аббревиатур - приводим ее в верхний регистр
  private static String normalizeWord(String word) {
    return ABBREVIATIONS_MAP.getOrDefault(word.toLowerCase(), word);
  }
}

## Лабораторна робота No4. Вдосконалення структури коду графічного редактора об’єктів на C++ (Kotlin)

1. Для усіх варіантів завдань необхідно дотримуватися вимог та
положень, викладених вище у порядку виконання роботи та методичних
рекомендаціях.
2. Номер варіанту завдання дорівнює номеру зі списку студентів у
журналі.
Студенти з непарним номером (1, 3, 5, . . .) програмують глобальний
статичний об'єкт класу MyEditor.
Студенти з парним номером (2, 4, 6, . . .) програмують динамічний
обєкт класу MyEditor, забезпечивши коректне його створення та знищення.
3. Усі кольори та стилі (за винятком "гумового" сліду) геометричних
форм – як у попередній лабораторній роботі No3. "Гумовий" слід при вводі
усіх фігур малювати пунктирною лінією.
4. Окрім чотирьох типів фігур, які були у попередніх лаб. No2 та 3,
запрограмувати ще введення та відображення двох нових фігур – лінія з
кружечками та каркас куба.
Кольори ліній та заповнення цих нових фігур студент визначає на свій
розсуд.
Для об’єктів типів лінії з кружечками та каркасу кубу відповідні класи
запрограмувати саме множинним успадкуванням. У цій лабораторній
роботі не дозволяється замінювати множинне спадкування, наприклад,
композицією. У першу чергу це стосується метода Show для нових фігур –
для відображення ліній треба використовувати виклики метода Show з класу
LineShape, для відображення кружечків – виклики метода Show з класу
EllipseShape, а для відображення прямокутників – виклики метода Show з
класу RectShape.
5. Для усіх шости типів форм зробити кнопки Toolbar з підказками
(tooltips)
6. У звіті повинна бути схема успадкування класів – діаграма класів.
Потрібно побудувати діаграму класів засобами Visual Studio C++.

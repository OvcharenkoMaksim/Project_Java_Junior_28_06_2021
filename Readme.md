 Проект создан для прохождения собеседования на Java Junior
 Проект автоматизирует работу по формированию моего основного запроса к БД одной из трех тестовых площадок проекта ПУР КС - Test, Demo, Rls.
 Запрос использует данные из Routecontext, Org, Doc, Doctype, Docstate объединяя их по ключам. 

 Описание работы приложения:
 1. Общее - приложение позволяет выбрать прараметр отбора записей в БД, принимает данные, валидирует их, предлагает выбрать вариант формирования ответа от БД (кол-во полей в ответе), подключается к БД, отправляет сформированный запрос в БД и отформатированный ответ выводит в консоль.

 2. Детали: 
 
а) предлагается выбор БД УФОСа тестовой площадки, к которой будет выполнено подключение (TSE-TEST, TSE-DEMO, TSE-RLS).

б) выбирается параметр отбора записей в БД - GLOBALDOCID или ROUTECONTEXTID.

в) принимаются значения параметра отбора записей, выполняется их валидация, удаление дублей, если они имеют место.

г) определяется состав полей, возвращаемых запросом.

д) формируется итоговый запрос к БД.

д) выполняется подключение к БД, выполняется запрос и ответ в отформатированном виде выводится в консоль.

 Применяемые технологии и решения - абстракция, наследование функциональный интерфейс, ввод-вывод, циклы, ветвления, работа с датами, работа с коллекциями (List, Set) и картами (Map), jdbc, Spring Core, реализованы элементы ORM, Junit, Mockito, проект создан на основе Maven.

 Состав классов в порядке их иерархии и цели каждого из классов:
 
 Start - стартовый класс приложения с методом main.

 RequestSubmission - вывести из созданной карты с ответом на запрос необходимые поля в нужной последовательности в консоль, для отображения пользоватею.    
   
 FieldsView - определение пользователем вида выводимого результата, в частности количество полей в ответе от БД, выбрав один из двух предложенных вариантов
	  
 Connect - реализация запроса к БД и получение в итоге карты объектов с данными полей запроса. Из данной карты формируется выводимый пользователю ответ на запрос. 
 
ConnectStenable - функциональный интерфейс с реализованным методом подключения к БД по подданым в метод аргументам, полученный запрос сохраняется в Map, карта возвращается методом, также имеет абстрактный метод возвращающий имя площадки, к БД которой подключаемся.

   ConnectTest - реализует ConnectStenable, подает реальные данные, необходимые для подключения к БД TSE-TEST, выступает как прокси.
   
   ConnectDemo - реализует ConnectStenable, подает реальные данные, необходимые для подключения к БД TSE-DEMO, выступает как прокси.
   
   ConnectRls - реализует ConnectStenable, подает реальные данные, необходимые для подключения к БД TSE-RLS, выступает как прокси.	
		     
   ConnectStend.properties - инверсирует управление данными для подключения в классах, реализующих интерфейс. Все данные вынесены в отдельный файл для удобства их корректировки  в отдельном xml-документе, а не в самом коде (в случае изменения данных для подключения (пароль, изменения в url, логин)). Не является классом, является частью реализации Spring. 
			       
RequestStructure - создает итоговый запрос к БД.	
		
Introducer - помогает пользователю выбрать параметр для отбора записей БД (guid или идентификатор БД), получеат значения данного параметра, выполняет проверку на корректность вводимых данных м удаляет дубли.			
		      
ValidatorArgumentFath - сервисный родительский класс, получает значения параметра отбора записей в БД УФОС, выполняет их валидацию, удаляет возможные дубли.	

   ValidatorArgumentGuid - сервисный класс-наследник ValidatorArgumentFath, принимает guid в качестве параметра запроса, выполняет валидацию вводимых значений и удаляет возможные дубли, переопределяет родительский метод.
	   
   ValidatorArgumentIdDB - сервисный класс-наследник ValidatorArgumentFath, принимает Идентификаторы БД в качестве параметра запроса, выполняет валидацию вводимых значений и удаляет возможные дубли, переопределяет родительский метод.	
				  
Responseline - создает объект, в котором будет храниться строка возвращенного от БД ответа на запрос. Реализует ORM - связывает поля объекта с полями результирующего запроса от реляционной БД.
			
applicationContext.xml - часть Spring, реализует внедрение зависимостей между объектами и инжектирует данные в поля объектов (для подключения к БД). Позволяет легко изменять данные подключения к БД через конфигурационный файл ConnectStend.properties.

Тестовые классы:
RequestSubmissionTest - тестирует метод presentingRequestScreen класса RequestSubmission.
ValidatorArgumentFathTest - тестирует метод eliminationOfDuplicates класса ValidatorArgumentFath.
В целом, методы принимающие данные из консроли тестированию посредством Junit не поддаются.

 Особенности архитектуры приложения - точки расширения и модификации:
 
 1. Легкая модификация данных для подключения к БД через файл ConnectStend.properties
 2. Добавление новой БД для подключения через создание нового класса, реализующего ConnectStenable
 3. Выбор нового параметра отбора записей в БД через создание дочернего класса от ValidatorArgumentFath
 4. Можно легко изменить вид выводимого в консоль ответа в одной строке, добавить новые варианты вывода.
 5. Также на всех этапах ввода значений с клавиатуры имеются контроли корретности вводимых значений.
 

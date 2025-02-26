<h1 align="center">Hi there, I'm <a>Ruzhalovich Ivan</a> 
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>

<h2 align="center">This is Spring-Security-Oauth2</h2>

<h3>1. Регистрация клиента в провайдере OAuth 2.0</h3>

Вы должны зарегистрировать своё приложение как клиент в провайдере OAuth 2.0:
Google, GitHub, или любой другой провайдер.
Получите client_id и client_secret после регистрации.
Задача: настроить эти параметры в проекте.

<h3>2. Интеграция Spring Security и OAuth 2.0</h3>

Настройте SecurityConfigurerAdapter.
В application.yml добавьте настройки клиента (например, для Google).
Используйте Authorization Code поток для аутентификации.
Задача: настроить приложение для работы с OAuth 2.0 авторизацией.

<h3>3. Разделение доступа на роли</h3>

На основе данных, возвращаемых провайдером (например, GitHub или Google), реализуйте функционал назначения ролей пользователю.
Пример ролей:
USER: имеет доступ к основным ресурсам.
ADMIN: имеет доступ к административным ресурсам.
Задача: настроить доступ к определенным эндпоинтам в зависимости от роли пользователя.

<h3>4. Получение и отображение профиля пользователя</h3>
   
После успешной аутентификации получите данные профиля пользователя из провайдера OAuth 2.0 (имя, email и т.д.).
Настройте контроллер, который будет получать эту информацию и передавать её на страницу профиля пользователя.

<h3>5. Отзыв доступа и выход из системы</h3>
   
Реализуйте механизм отзыва доступа:
Добавьте функционал выхода из системы и отзыв OAuth-токена.
Для Google или других провайдеров это можно сделать через вызов logout URL.
Задача: реализовать безопасный выход из системы и отзыв токена.

<h3>6. Логирование действий пользователя</h3>
   
Интегрируйте логирование ключевых действий:
Успешная аутентификация.
Выход из системы.
Отзыв доступа.
Используйте стандартное логирование Spring или SLF4J для записи этих действий в журнал.
Задача: добавить логирование этих событий.

<h3>7. Защита эндпоинтов</h3>

Защитите чувствительные части приложения:
Запретите доступ к страницам, таким как админ-панель, для неавторизованных пользователей.
Задача: реализовать правильную защиту, чтобы защищённые эндпоинты были доступны только авторизованным пользователям и определённым ролям.
@Service  
@AllArgsConstructor  
public class SocialAppService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {  
      
<h3>8. Обработка ошибок</h3>

Добавьте обработчики для ошибок:
Ошибки аутентификации.
Недостаток прав доступа (403 Forbidden).
Ошибка истечения токена и другие сценарии.
Задача: реализовать контроллер для обработки этих ошибок и выводить корректные сообщения пользователю.

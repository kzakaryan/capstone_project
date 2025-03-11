# Statement vs PreparedStatement

## Statement

A `Statement` is used to execute basic SQL queries that do not require parameters or repeated execution. It's vulnerable to SQL injection attacks since user input is inserted directly into the SQL query.

**Example:**

```java
Statement statement = connection.createStatement();
String query = "SELECT * FROM users WHERE username = '" + username + "'";
ResultSet resultSet = statement.executeQuery(query);
```
## PreparedStatement
A `PreparedStatement` is used to execute precompiled SQL queries with placeholders (?). It binds parameters to these placeholders, preventing SQL injection and improving performance by reusing the compiled query.

**Example:**

```java
PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
preparedStatement.setString(1, username);
ResultSet resultSet = preparedStatement.executeQuery();
```

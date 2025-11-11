<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Todo List</title>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"
    >
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flexboxgrid/6.3.1/flexboxgrid.min.css"
          type="text/css">
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.colors.min.css"
    >
</head>
<body>

<header class="container">
    <div class=" row between-xs">
        <h1>Todo List</h1>
        <a role="button" href="<%= request.getContextPath() %>/create.jsp">Create Todo</a>
    </div>
</header>

<main class="container">
    <div>
        <%
            java.util.List<com.landrycarroll.javawebkata.entities.Todo> todos =
                    (java.util.List<com.landrycarroll.javawebkata.entities.Todo>) request.getAttribute("todos");

            if (todos.isEmpty()) {

        %>
        <article>
            Congratulations! You have no todos at the moment. Ready to add more? <a
                href="<%= request.getContextPath() %>/create.jsp">Create a New
            Todo</a>
        </article>
        <%
            }

            for (var todo : todos) {
        %>
        <article class="
        <%= todo.isCompleted() ? "pico-background-jade-850" : "pico-background-gray-800"%>">
            <div class="row between-xs" style="margin:0;">
                <div>
                    <h2>
                        <%= todo.getTitle()%>
                    </h2>
                </div>
                <div class="box">
                    <div role="group">
                        <form method="post" action="<%=request.getContextPath()%>/todos/toggle">
                            <input type="hidden" name="action" value="toggle"/>
                            <input type="hidden" name="id" value="<%= todo.getId() %>"/>
                            <button style="border-radius: .25rem 0 0 .25rem;"
                                    type="submit"
                                    class="<%= todo.isCompleted() ? "outline" : ""%> contrast">
                                <%= todo.isCompleted() ? "Mark as Incomplete" : "Mark as Complete"%>
                            </button>
                        </form>

                        <form method="post" action="<%=request.getContextPath()%>/todos/delete">
                            <input type="hidden" name="action" value="toggle"/>
                            <input type="hidden" name="id" value="<%= todo.getId() %>"/>
                            <button type="submit" class="outline contrast"
                                    style="border-radius: 0 .25rem .25rem 0;">
                                Delete
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <p>
                    <%= todo.getDescription()%>
                </p>
            </div>
        </article>
        <% } %>
    </div>
</main>

<footer></footer>
</body>
</html>

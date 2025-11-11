<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Create a Todo</title>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"
    >
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.colors.min.css"
    >
</head>
<body>

<header class="container">
    <a href="<%= request.getContextPath()%>/todos">← View all Todos</a>
    <h1>Create a Todo</h1>
</header>

<main class="container">
    <form method="post" action="<%=request.getContextPath()%>/todos/create">
        <fieldset>
            <label>
                Title:
                <input type="text" name="title"/>
            </label>
            <label>
                Description:
                <input type="text" name="description"/>
            </label>
        </fieldset>
        <article
                class="pico-background-red-850"
                style="display: <%= request.getAttribute("error") == null ? "none" : "block" %>"
        >
            <%=request.getAttribute("error")
            %>
        </article>
        <button type="submit">Add Todo</button>
    </form>
</main>

<footer></footer>
</body>
</html>

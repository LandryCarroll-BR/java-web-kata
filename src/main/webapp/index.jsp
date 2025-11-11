<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todos</title>
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
<main class="container">
    <article>
        <h1 class="row center-xs">
            Get Started!
        </h1>
        <div class="row center-xs">
            <div role="group" style="max-width: 480px; margin: 0 auto;">
                <a href="<%= request.getContextPath()%>/todos" role="button" class="secondary">View all Todos</a>
                <a href="create.jsp" role="button">Create a Todo</a>
            </div>
        </div>
    </article>
</Main>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ESCOMHeroes - Ranking de Jugadores</title>
	<link rel="stylesheet" href="estilos/estilosES.css">
</head>
<body>
	<header>
		<h1>ESCOMHeroes</h1>
		<nav>
			<ul>
				<li><a href="index.html">Inicio</a></li>
				<li><a href="Ranking.jsp">Ranking</a></li>
				<li><a href="altas.html">Mi Cuenta</a></li>
			</ul>
		</nav>
	</header>

	<main>
		<section>
			<h2>Ranking de Jugadores</h2>
			<form method="post" action="Ranking">
				<input type="submit" name="orderByLevel" value="Ordenar por nivel">
				<input type="submit" name="orderByScore" value="Ordenar por puntuación">
			</form>
			<table>
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>Nombre de Usuario</th>
						<th>Nivel</th>
						<th>Dinero</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="jugador" items="${jugadores}">
						<tr>
							<td>${jugador.nombre}</td>
							<td>${jugador.apellido}</td>
							<td>${jugador.nombreUsuario}</td>
							<td>${jugador.nivel}</td>
							<td>${jugador.dinero}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
	</main>

	<footer>
		<p>ESCOMHeroes &copy; 2023 - Todos los derechos reservados</p>
	</footer>
</body>
</html>

<?php

session_start();
$cadena_conexion = 'mysql:dbname=aplicacion_web;host=127.0.0.1';
$usuario_bd = 'root';
$clave_bd = '';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (!empty($_POST["usuario"]) && !empty($_POST["contra"])) {

        try {
            $bd = new PDO($cadena_conexion, $usuario_bd, $clave_bd);

            $preparada = $bd->prepare("SELECT id_usuario, nombre, password_hash FROM usuarios WHERE nombre = ?");
            $preparada->execute([$_POST["usuario"]]);
            $usuario = $preparada->fetch(PDO::FETCH_ASSOC);

            if ($usuario && password_verify($_POST["contra"], $usuario["password_hash"])) {
                $_SESSION["id"]      = $usuario["id_usuario"];
                $_SESSION["usuario"] = $usuario["nombre"];

                header("Location: index.php");
                exit();
            } else {
                $error = "Usuario o contraseña incorrectos.";
            }

        } catch (PDOException $e) {
            $error = "Error de conexión: " . $e->getMessage();
        }

    } else {
        $error = "Rellena todos los campos.";
    }
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de sesión - Kata_MMA</title>
    <link rel="stylesheet" href="estilos/login.css">
    <link rel="icon" type="image/x-icon" href="imagenes/logo.png">
</head>
<body>
    <header>
        <h1>KATA - MMA</h1>
    </header>
    <hr>
    <main>
        <form method="post">
            <fieldset>
                <h2>INICIAR SESIÓN</h2>

                <?php if (!empty($error)): ?>
                    <p style="color: red;"><?php echo htmlspecialchars($error); ?></p>
                <?php endif; ?>

                Introduce un nombre de usuario:
                <input type="text" placeholder="usuario123" required name="usuario"><br>
                Introduce una contraseña:
                <input type="password" required name="contra"><br>

                <hr id="linea">
                <div id="botones">
                    <input type="submit" value="Iniciar sesión">
                    <a href="index.html">Volver atrás</a>
                </div>
                <p>¿No tienes cuenta? <a href="registro.php">Regístrate</a></p>
            </fieldset>
        </form>
    </main>
    <footer>
        <div class="RRSS">
            <a href="https://www.instagram.com"><img src="imagenes/insta.png" alt="insta" style="width:24px;height:24px;"></a>
            <a href="https://www.youtube.com"><img src="imagenes/yt.png" alt="youtube" style="width:24px;height:24px;"></a>
            <a href="https://www.x.com"><img src="imagenes/x.png" alt="x" style="width:24px;height:24px;"></a>
        </div>
        <p>Derechos de autor © 2025 Kata_MMA</p>
        <p><a href="https://www.google.com">Avisos legales</a></p>
    </footer>
</body>
</html>
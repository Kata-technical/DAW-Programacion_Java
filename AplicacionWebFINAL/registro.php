<?php

session_start();
$cadena_conexion = 'mysql:dbname=aplicacion_web;host=127.0.0.1';
$usuario_bd = 'root';
$clave_bd = '';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (!empty($_POST["email"]) && !empty($_POST["usuario"]) && !empty($_POST["clave"])) {

        $password = password_hash($_POST["clave"], PASSWORD_DEFAULT);

        try {
            $bd = new PDO($cadena_conexion, $usuario_bd, $clave_bd);

            $check = $bd->prepare("SELECT id_usuario FROM usuarios WHERE email = ? OR nombre = ?");
            $check->execute([$_POST["email"], $_POST["usuario"]]);

            if ($check->rowCount() > 0) {
                $error = "El email o usuario ya están registrados.";
            } else {
                $preparada = $bd->prepare("INSERT INTO usuarios (nombre, email, password_hash) VALUES (?, ?, ?)");
                $preparada->execute([$_POST["usuario"], $_POST["email"], $password]);

                $_SESSION["usuario"] = $_POST["usuario"];
                $_SESSION["id"] = $bd->lastInsertId();

                header("Location: index.php");
                exit();
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
    <title>Registro - Kata_MMA</title>
    <link rel="stylesheet" href="estilos/registro.css">
    <link rel="icon" type="image/x-icon" href="imagenes/logo.png">
</head>
<body>
    <header>
        <h1>KATA - MMA</h1>
    </header>
    <hr>
    <main>
        <form action="" method="post">
            <fieldset>
                <h2>REGISTRO</h2>

                <?php if (!empty($error)): ?>
                    <p style="color: red;"><?php echo htmlspecialchars($error); ?></p>
                <?php endif; ?>

                Introduce un email:
                <input type="email" placeholder="ejemplo@email.com" name="email" required><br>
                Introduce un nombre de usuario:
                <input type="text" placeholder="usuario123" name="usuario" required><br>
                Introduce una contraseña:
                <input type="password" required name="clave"><br>

                <div>
                    <input type="checkbox" required> Aceptar
                    <a class="enlace" href="https://www.google.com">términos</a> y
                    <a class="enlace" href="https://www.google.com">condiciones del servicio</a>
                </div>
                <br>
                <hr id="linea">
                <div id="botones">
                    <input type="submit" value="Registrarse">
                    <a href="index.html">Volver atrás</a>
                </div>
                <hr id="linea">
                <div>
                    <p>¿Ya tienes una cuenta? <a href="login.php">Inicia sesión</a></p>
                </div>
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
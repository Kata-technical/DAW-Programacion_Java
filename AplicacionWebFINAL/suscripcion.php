<?php
session_start();

if (!isset($_SESSION["usuario"])) {
    header("Location: login.php");
    exit();
}

$cadena_conexion = 'mysql:dbname=aplicacion_web;host=127.0.0.1';
$usuario_bd = 'root';
$clave_bd = '';

$error = '';
$exito = '';

$ya_suscrito = false;
try {
    $bd = new PDO($cadena_conexion, $usuario_bd, $clave_bd);
    $stmt = $bd->prepare("SELECT subscrito FROM usuarios WHERE id_usuario = ?");
    $stmt->execute([$_SESSION["id"]]);
    $fila = $stmt->fetch(PDO::FETCH_ASSOC);
    $ya_suscrito = $fila && $fila['subscrito'];
} catch (PDOException $e) {
    $error = "Error al comprobar suscripción.";
}

if ($_SERVER["REQUEST_METHOD"] == "POST" && !$ya_suscrito) {
    $tarjeta = $_POST["tarjeta"] ?? '';
    $caducidad = $_POST["caducidad"] ?? '';
    $cvv = $_POST["cvv"] ?? '';

    if (empty($tarjeta) || empty($caducidad) || empty($cvv)) {
        $error = "Rellena todos los campos.";
    } elseif (strlen($tarjeta) !== 16) {
        $error = "El número de tarjeta debe tener exactamente 16 dígitos.";
    } elseif (strlen($cvv) !== 3) {
        $error = "El CVV debe tener exactamente 3 dígitos.";
    } else {
        try {
            $bd = new PDO($cadena_conexion, $usuario_bd, $clave_bd);
            $update = $bd->prepare("UPDATE usuarios SET subscrito = TRUE WHERE id_usuario = ?");
            $update->execute([$_SESSION["id"]]);

            header("Location: directos.html");
            exit();

        } catch (PDOException $e) {
            $error = "Error al procesar la suscripción.";
        }
    }
}
?>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Suscripción - Kata_MMA</title>
    <link rel="stylesheet" href="estilos/suscripcion.css">
    <link rel="icon" type="image/x-icon" href="imagenes/logo.png">
</head>

<body>
    <header>
        <h1>KATA - MMA</h1>
    </header>
    <hr>
    <main>

        <?php if ($ya_suscrito): ?>
            <!-- El usuario ya tiene suscripción activa -->
            <fieldset style="text-align: center; padding: 40px;">
                <h2>✅ Ya estás suscrito</h2>
                <p>Tu suscripción está activa. Disfruta del acceso a los directos.</p>
                <br>
                <a href="directos.html" class="botonRelevante">Ir a Directos</a>
                <br><br>
                <a href="index.php">Volver al inicio</a>
            </fieldset>

        <?php else: ?>
            <form method="post">
                <fieldset>
                    <h2>SUSCRIPCIÓN</h2>

                    <?php if (!empty($error)): ?>
                        <p style="color: red;"><?php echo htmlspecialchars($error); ?></p>
                    <?php endif; ?>

                    <div id="contenedor">
                        <div id="contenido">
                            Introduce un número de tarjeta:
                            <input type="text" name="tarjeta" maxlength="16" minlength="16"
                                   placeholder="1234567890123456" required><br>

                            Introduce la fecha de caducidad:
                            <input type="month" name="caducidad" required><br>

                            Introduce el CVV:
                            <input type="text" name="cvv" maxlength="3" minlength="3"
                                   placeholder="123" required>
                        </div>
                        <div id="span">
                            Acceder a los DIRECTOS <br><br> <span>4.99€/mes</span>
                        </div>
                    </div>

                    <hr id="linea">
                    <div id="botones">
                        <input type="submit" value="Suscribirse" class="botonRelevante"><br>
                        <a href="index.php">Volver atrás</a>
                    </div>
                </fieldset>
            </form>
        <?php endif; ?>

    </main>
    <footer>
        <div class="RRSS">
            <a href="https://www.instagram.com"><img src="imagenes/insta.png" alt="insta"
                    style="width: 24px; height: 24px;"></a>
            <a href="https://www.youtube.com"><img src="imagenes/yt.png" alt="youtube"
                    style="width: 24px; height: 24px;"></a>
            <a href="https://www.x.com"><img src="imagenes/x.png" alt="x" style="width: 24px; height: 24px;"></a>
        </div>
        <p>Derechos de autor © 2025 Kata_MMA</p>
        <p><a href="https://www.google.com">Avisos legales</a></p>
    </footer>
</body>

</html>
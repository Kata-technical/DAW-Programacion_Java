<?php
session_start();

$cadena_conexion = 'mysql:dbname=aplicacion_web;host=127.0.0.1';
$usuario_bd = 'root';
$clave_bd = '';

$sesion_activa = isset($_SESSION["usuario"]);
$nombre_usuario = $sesion_activa ? $_SESSION["usuario"] : '';
$suscrito = false;

$categorias = [];
try {
    $bd = new PDO($cadena_conexion, $usuario_bd, $clave_bd);

    if ($sesion_activa) {
        $stmt = $bd->prepare("SELECT subscrito FROM usuarios WHERE id_usuario = ?");
        $stmt->execute([$_SESSION["id"]]);
        $fila = $stmt->fetch(PDO::FETCH_ASSOC);
        $suscrito = $fila && $fila['subscrito'];
    }

    $stmt = $bd->query("SELECT * FROM categorias ORDER BY nombre ASC");
    $categorias = $stmt->fetchAll(PDO::FETCH_ASSOC);

} catch (PDOException $e) {}

$num_carrito = 0;
if (isset($_SESSION["carrito"])) {
    foreach ($_SESSION["carrito"] as $cantidad) {
        $num_carrito += $cantidad;
    }
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Categorías - Kata_MMA</title>
    <link rel="stylesheet" href="estilos/productos.css">
    <link rel="icon" type="image/x-icon" href="imagenes/logo.png">
</head>
<body>
    <header>
        <div>
            <img src="imagenes/logo.png" alt="icono_kataMMA" style="width: 50px; height: 50px;">
            <h1>Kata_MMA</h1>
        </div>
        <nav>
            <a class="paginaImportante" href="index.php">Noticias</a>
            <?php if ($suscrito): ?>
                <a class="paginaImportante" href="directos.html">Directos</a>
            <?php else: ?>
                <a class="paginaImportante" href="suscripcion.php">Directos</a>
            <?php endif; ?>
            <a class="paginaImportante" href="estadisticas.html">Estadísticas</a>
            <a class="paginaImportante" href="categorias.php">Productos</a>

            <?php if ($sesion_activa): ?>
                <span class="paginaSecundaria saludo">Hola, <strong><?php echo htmlspecialchars($nombre_usuario); ?></strong></span>
                <a class="paginaSecundaria" href="perfil.html">Perfil</a>
                <a class="paginaSecundaria" href="carrito.php">
                    🛒 Carrito <?php if ($num_carrito > 0): ?><span class="badge"><?php echo $num_carrito; ?></span><?php endif; ?>
                </a>
                <a class="paginaSecundaria" href="logout.php">Cerrar sesión</a>
            <?php else: ?>
                <a class="paginaSecundaria" href="registro.php">Registrarse o iniciar sesión</a>
            <?php endif; ?>
        </nav>
    </header>
    <hr>

    <div id="portada">
        <h1>PRODUCTOS</h1>
        <p>Selecciona una categoría para ver los productos disponibles.</p>
    </div>
    <hr>

    <main>
        <h1>CATEGORÍAS</h1>
        <br>

        <?php if (empty($categorias)): ?>
            <p style="padding: 20px; color: #888;">No hay categorías disponibles.</p>
        <?php else: ?>
            <div class="contenedores">
                <?php foreach ($categorias as $cat): ?>
                    <a href="productos.php?categoria=<?php echo $cat['id_categoria']; ?>" class="tarjeta-categoria">
                        <div class="productos">
                            <h2><?php echo htmlspecialchars($cat['nombre']); ?></h2>
                            <?php if (!empty($cat['descripcion'])): ?>
                                <p><?php echo htmlspecialchars($cat['descripcion']); ?></p>
                            <?php endif; ?>
                            <span class="boton-ver">Ver productos →</span>
                        </div>
                    </a>
                <?php endforeach; ?>
            </div>
        <?php endif; ?>
    </main>

    <footer>
        <div class="RRSS">
            <a href="https://www.instagram.com"><img src="imagenes/insta.png" alt="insta" style="width: 24px; height: 24px;"></a>
            <a href="https://www.youtube.com"><img src="imagenes/yt.png" alt="youtube" style="width: 24px; height: 24px;"></a>
            <a href="https://www.x.com"><img src="imagenes/x.png" alt="x" style="width: 24px; height: 24px;"></a>
        </div>
        <p>Derechos de autor © 2025 Kata_MMA</p>
        <p><a href="https://www.google.com">Avisos legales</a></p>
    </footer>
</body>
</html>
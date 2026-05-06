<?php
session_start();

// Requiere sesión activa
if (!isset($_SESSION["usuario"])) {
    header("Location: login.php");
    exit();
}

$cadena_conexion = 'mysql:dbname=aplicacion_web;host=127.0.0.1';
$usuario_bd = 'root';
$clave_bd = '';

$suscrito = false;
$mensaje = '';
$error = '';

// Eliminar un producto del carrito
if (isset($_GET["eliminar"])) {
    $id_eliminar = (int)$_GET["eliminar"];
    unset($_SESSION["carrito"][$id_eliminar]);
    header("Location: carrito.php");
    exit();
}

// Vaciar carrito
if (isset($_GET["vaciar"])) {
    unset($_SESSION["carrito"]);
    header("Location: carrito.php");
    exit();
}

// Finalizar compra
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["finalizar"])) {
    if (empty($_SESSION["carrito"])) {
        $error = "El carrito está vacío.";
    } else {
        try {
            $bd = new PDO($cadena_conexion, $usuario_bd, $clave_bd);
            $bd->beginTransaction();

            foreach ($_SESSION["carrito"] as $id_producto => $cantidad) {
                // Verificar stock actual
                $stmt = $bd->prepare("SELECT stock, precio FROM productos WHERE id_producto = ?");
                $stmt->execute([$id_producto]);
                $prod = $stmt->fetch(PDO::FETCH_ASSOC);

                if (!$prod || $prod['stock'] < $cantidad) {
                    $bd->rollBack();
                    $error = "Stock insuficiente para uno o más productos. Revisa el carrito.";
                    break;
                }

                $total = $prod['precio'] * $cantidad;

                // Insertar pedido
                $insert = $bd->prepare(
                    "INSERT INTO pedidos (id_usuario, id_producto, cantidad, total) VALUES (?, ?, ?, ?)"
                );
                $insert->execute([$_SESSION["id"], $id_producto, $cantidad, $total]);

                // Descontar stock
                $update = $bd->prepare(
                    "UPDATE productos SET stock = stock - ? WHERE id_producto = ?"
                );
                $update->execute([$cantidad, $id_producto]);
            }

            if (empty($error)) {
                $bd->commit();
                unset($_SESSION["carrito"]);
                $mensaje = "¡Compra realizada con éxito! Gracias por tu pedido.";
            }

        } catch (PDOException $e) {
            $bd->rollBack();
            $error = "Error al procesar la compra. Inténtalo de nuevo.";
        }
    }
}

// Cargar datos de los productos del carrito
$items = [];
$total_global = 0;

if (!empty($_SESSION["carrito"])) {
    try {
        $bd = new PDO($cadena_conexion, $usuario_bd, $clave_bd);

        $stmt = $bd->prepare("SELECT subscrito FROM usuarios WHERE id_usuario = ?");
        $stmt->execute([$_SESSION["id"]]);
        $fila = $stmt->fetch(PDO::FETCH_ASSOC);
        $suscrito = $fila && $fila['subscrito'];

        $ids = array_keys($_SESSION["carrito"]);
        $placeholders = implode(',', array_fill(0, count($ids), '?'));
        $stmt2 = $bd->prepare("SELECT * FROM productos WHERE id_producto IN ($placeholders)");
        $stmt2->execute($ids);
        $prods = $stmt2->fetchAll(PDO::FETCH_ASSOC);

        foreach ($prods as $p) {
            $cantidad = $_SESSION["carrito"][$p['id_producto']];
            $subtotal = $p['precio'] * $cantidad;
            $total_global += $subtotal;
            $items[] = [
                'producto' => $p,
                'cantidad' => $cantidad,
                'subtotal' => $subtotal,
            ];
        }

    } catch (PDOException $e) {}
}

$num_carrito = 0;
if (isset($_SESSION["carrito"])) {
    foreach ($_SESSION["carrito"] as $c) $num_carrito += $c;
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrito - Kata_MMA</title>
    <link rel="stylesheet" href="estilos/productos.css">
    <link rel="icon" type="image/x-icon" href="imagenes/logo.png">
    <style>
        table { width: 100%; border-collapse: collapse; margin: 20px 0; }
        th, td { padding: 12px 16px; border-bottom: 1px solid #333; text-align: left; }
        th { background: #1a1a1a; }
        .total-row td { font-weight: bold; font-size: 1.1em; border-top: 2px solid #555; }
        .acciones { display: flex; gap: 16px; margin-top: 20px; align-items: center; flex-wrap: wrap; }
        .btn-peligro { color: #e55; border: 1px solid #e55; padding: 8px 16px; text-decoration: none; border-radius: 4px; }
        .btn-peligro:hover { background: #e55; color: #fff; }
        .btn-comprar { background: #f90; color: #000; border: none; padding: 10px 24px; font-size: 1em; border-radius: 4px; cursor: pointer; font-weight: bold; }
        .btn-comprar:hover { background: #e80; }
        .carrito-vacio { text-align: center; padding: 60px 20px; color: #888; }
        .carrito-vacio a { color: #f90; }
    </style>
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
            <span class="paginaSecundaria saludo">Hola, <strong><?php echo htmlspecialchars($_SESSION["usuario"]); ?></strong></span>
            <a class="paginaSecundaria" href="perfil.html">Perfil</a>
            <a class="paginaSecundaria" href="carrito.php">
                🛒 Carrito <?php if ($num_carrito > 0): ?><span class="badge"><?php echo $num_carrito; ?></span><?php endif; ?>
            </a>
            <a class="paginaSecundaria" href="logout.php">Cerrar sesión</a>
        </nav>
    </header>
    <hr>

    <main style="padding: 20px 40px;">
        <h1>🛒 Mi Carrito</h1>

        <?php if (!empty($mensaje)): ?>
            <p style="color: green; font-size: 1.1em; padding: 16px; background: #0a2a0a; border-radius: 6px;">
                ✅ <?php echo htmlspecialchars($mensaje); ?>
            </p>
            <br>
            <a href="categorias.php" style="color: #f90;">← Seguir comprando</a>

        <?php elseif (!empty($error)): ?>
            <p style="color: #e55;"><?php echo htmlspecialchars($error); ?></p>

        <?php endif; ?>

        <?php if (empty($items) && empty($mensaje)): ?>
            <div class="carrito-vacio">
                <p style="font-size: 3em;">🛒</p>
                <p>Tu carrito está vacío.</p>
                <a href="categorias.php">Ver productos</a>
            </div>

        <?php elseif (!empty($items)): ?>
            <table>
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Subtotal</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($items as $item): ?>
                        <tr>
                            <td>
                                <?php if (!empty($item['producto']['imagen_url'])): ?>
                                    <img src="<?php echo htmlspecialchars($item['producto']['imagen_url']); ?>"
                                         alt="" style="width:50px; height:50px; object-fit:cover; margin-right:10px; vertical-align:middle;">
                                <?php endif; ?>
                                <?php echo htmlspecialchars($item['producto']['nombre']); ?>
                            </td>
                            <td><?php echo number_format($item['producto']['precio'], 2, ',', '.'); ?> €</td>
                            <td><?php echo $item['cantidad']; ?></td>
                            <td><?php echo number_format($item['subtotal'], 2, ',', '.'); ?> €</td>
                            <td>
                                <a href="carrito.php?eliminar=<?php echo $item['producto']['id_producto']; ?>"
                                   class="btn-peligro"
                                   onclick="return confirm('¿Eliminar este producto del carrito?')">Eliminar</a>
                            </td>
                        </tr>
                    <?php endforeach; ?>
                    <tr class="total-row">
                        <td colspan="3">TOTAL</td>
                        <td colspan="2"><?php echo number_format($total_global, 2, ',', '.'); ?> €</td>
                    </tr>
                </tbody>
            </table>

            <div class="acciones">
                <form method="post">
                    <button type="submit" name="finalizar" class="btn-comprar">
                        ✅ Finalizar compra
                    </button>
                </form>
                <a href="carrito.php?vaciar=1" class="btn-peligro"
                   onclick="return confirm('¿Vaciar el carrito?')">🗑 Vaciar carrito</a>
                <a href="categorias.php" style="color: #aaa;">← Seguir comprando</a>
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
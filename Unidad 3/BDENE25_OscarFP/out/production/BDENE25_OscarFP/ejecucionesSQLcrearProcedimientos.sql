CREATE FUNCTION fnExisteAlojamiento (@nombreAlojamiento varchar(40)) RETURNS varchar(8) as BEGIN
    DECLARE @codigo varchar(8);
    SELECT @codigo = codigo + '' FROM ALOJAMIENTO WHERE @nombreAlojamiento = nombre
    IF @codigo = ''
        SET @codigo = NULL
    RETURN @codigo
END

CREATE PROCEDURE prNombreSedeHotel (
    @nombreHotel varchar(40),
    @nombreSedeHotel varchar(40) OUTPUT
) as BEGIN
    SELECT @nombreSedeHotel = S.nombre FROM ALOJAMIENTO S WHERE S.codigo = (
        SELECT H.hotelsede FROM HOTEL H INNER JOIN ALOJAMIENTO A ON H.cod_hotel = A.codigo WHERE A.nombre = @nombreHotel
    )
    IF @nombreSedeHotel = ''
        SET @nombreSedeHotel = 'SIN SEDE'
END

CREATE PROCEDURE prActividades (
    @nombreAlojamiento varchar(40)
) as BEGIN
    SELECT nombre FROM ACTIVIDAD WHERE codigo IN (
        SELECT cod_actividad FROM ALOJAMIENTO_ACTIVIDAD WHERE cod_alojamiento = (
            SELECT A.codigo FROM ALOJAMIENTO A WHERE A.nombre = @nombreAlojamiento
        )
    )
END
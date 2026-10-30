package pe.edu.upeu.vetandes.security;
import java.util.*;
public final class PermisosPorRol  {
    private PermisosPorRol() {
    }
    public static Set<Permiso> obtener(String rol) {
        return switch(rol) {
            case "ADMIN"->EnumSet.allOf(Permiso.class);
            case "RECEPCIONISTA"->EnumSet.of(Permiso.PRESTACION_LEER,Permiso.MASCOTA_LEER,Permiso.MASCOTA_ESCRIBIR,Permiso.ATENCION_LEER,Permiso.ATENCION_REGISTRAR);
            case "VETERINARIO"->EnumSet.of(Permiso.PRESTACION_LEER,Permiso.PRESTACION_ESCRIBIR,Permiso.MASCOTA_LEER,Permiso.ATENCION_LEER,Permiso.ATENCION_ANULAR,Permiso.REPORTE_VER);
            default->Set.of();
        }
        ;
    }
}

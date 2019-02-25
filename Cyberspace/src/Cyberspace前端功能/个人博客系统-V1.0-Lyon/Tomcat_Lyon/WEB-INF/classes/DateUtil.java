package blog.flowingsun.util;
import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {
    public DateUtil() {
    }

    public static Timestamp d2t(Date d) {
        return d == null ? null : new Timestamp(d.getTime());
    }

    public static Date t2d(Timestamp t) {
        return t == null ? null : new Date(t.getTime());
    }
}

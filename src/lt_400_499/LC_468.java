package lt_400_499;

/**
 * string
 * [468] Validate IP Address
 * 这里可能会出错的一个点就是 "2001:0db8:85a3::0:8A2E:0370:7334:".split(":").length的结果是8，最后一个':'之后是空串，将不会参与分割
 */
public class LC_468 {
    private static final String NAN = "Neither";
    private static final String IPV4 = "IPv4";
    private static final String IPV6 = "IPv6";

    /**
     * IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers,
     * each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
     * Besides, leading zeros in the IPv4 is invalid. For example, the address  172.16.254.01 is invalid.
     *
     * 第一次忘记检查rg长度, 导致Integer.parseInt溢出
     * @param ip
     * @return
     */
    private String ipv4Analyze(String ip) {
        if (ip.length() > 19 || ip.length() < 7) return NAN;
        char[] chs = ip.toCharArray();
        for (char c: chs) {
            if (!(c=='.' || (c >= '0' && c <= '9'))) return NAN;
        }
        String[] segment = ip.split("\\.");
        if (chs[chs.length-1]=='.' || segment.length != 4) return NAN;
        for (String rg: segment) {
            if (rg.length() == 0 || (rg.length() > 1 && rg.startsWith("0")) || rg.length() > 3 || Integer.parseInt(rg) > 255) {
                return NAN;
            }
        }
        return IPV4;
    }

    /**
     * IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits.
     * The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one.
     * Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones,
     * so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).
     *
     * However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity.
     * For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
     *
     * Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
     * @param ip
     * @return
     */
    private String ipv6Analyze(String ip) {
        if (ip.length() > 39 || ip.length() < 15) return NAN;
        char[] chs = ip.toCharArray();
        for (char c: chs) {
            if (!(c == ':' || (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                return NAN;
            }
        }
        // 尾部为:的时候，将不会把:的空串split出来
        String[] segment = ip.split(":");
        if (chs[chs.length-1]==':' || segment.length != 8) return NAN;
        for (String rg: segment) {
            if (rg.length() <= 0 || rg.length() > 4) {
                return NAN;
            }
        }
        return IPV6;
    }
    public String validIPAddress(String IP) {
        if (IP == null || IP.length() <= 0) return NAN;
        boolean dot = IP.contains(".");
        boolean colons = IP.contains(":");
        if (!dot && !colons) return NAN;
        if (dot && colons) return NAN;
        if (dot) {
            return ipv4Analyze(IP);
        } else {
            return ipv6Analyze(IP);
        }
    }

    public static void main(String ...args) {
        LC_468 lc_468 = new LC_468();
        String ip = "172.16.254.1";
        System.out.println(lc_468.validIPAddress(ip));
        System.out.println(lc_468.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
        System.out.println(lc_468.validIPAddress("256.256.256.256"));
        System.out.println(lc_468.validIPAddress("255.182.09.27"));

        System.out.println("2001:0db8:85a3::0:8A2E:0370:7334:".split(":").length);
    }
}

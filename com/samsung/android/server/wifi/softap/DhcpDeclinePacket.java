package com.samsung.android.server.wifi.softap;

import com.samsung.android.server.wifi.mobilewips.external.NetworkConstants;
import java.net.Inet4Address;
import java.nio.ByteBuffer;

/* access modifiers changed from: package-private */
/* compiled from: DhcpPacket */
public class DhcpDeclinePacket extends DhcpPacket {
    DhcpDeclinePacket(int transId, short secs, Inet4Address clientIp, Inet4Address yourIp, Inet4Address nextIp, Inet4Address relayIp, byte[] clientMac) {
        super(transId, secs, clientIp, yourIp, nextIp, relayIp, clientMac, false);
    }

    @Override // com.samsung.android.server.wifi.softap.DhcpPacket
    public String toString() {
        String s = super.toString();
        return s + " DECLINE";
    }

    @Override // com.samsung.android.server.wifi.softap.DhcpPacket
    public ByteBuffer buildPacket(int encap, short destUdp, short srcUdp) {
        ByteBuffer result = ByteBuffer.allocate(NetworkConstants.ETHER_MTU);
        fillInPacket(encap, this.mClientIp, this.mYourIp, destUdp, srcUdp, result, (byte) 1, false);
        result.flip();
        return result;
    }

    /* access modifiers changed from: package-private */
    @Override // com.samsung.android.server.wifi.softap.DhcpPacket
    public void finishPacket(ByteBuffer buffer) {
        addTlv(buffer, (byte) 53, (byte) 4);
        addTlv(buffer, (byte) 61, getClientId());
        addTlvEnd(buffer);
    }
}

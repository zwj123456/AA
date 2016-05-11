import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Test3 {
	public static void main(String[] args) throws IOException {
	}
	
	public void aa() throws IOException {

		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Selector selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.socket().bind(new InetSocketAddress(8080));
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		while(true){
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iter = selectedKeys.iterator();
			while (iter.hasNext()) {
				SelectionKey selectionKey = (SelectionKey) iter.next();
				if((selectionKey.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
					
				}
			}
		}
	}
}

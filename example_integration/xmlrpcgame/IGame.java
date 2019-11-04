package studio.lineage2.gameserver.xmlrpcgame;


public interface IGame
{
	String getPlayersNameByAccount(String account);

	String addItemByPlayerName(String playerName, int itemId, long itemCount);

	String getTopPvP(int count);

	String getTopPK(int count);
}

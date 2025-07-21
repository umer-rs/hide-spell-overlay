package com.hidespellbooktooltip;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(name = "Hide Spellbook Tooltip")
public class HideSpellbookTooltipPlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	private ClientThread clientThread;

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded widgetLoaded)
	{
		if (widgetLoaded.getGroupId() != 218 || client.getWidget(InterfaceID.MagicSpellbook.TOOLTIP) == null)
		{
			return;
		}
		clientThread.invoke(() -> client.getWidget(InterfaceID.MagicSpellbook.TOOLTIP).setHidden(true));
	}

	@Override
	protected void startUp() throws Exception
	{
		if (client.getWidget(InterfaceID.MagicSpellbook.TOOLTIP) == null)
		{
			return;
		}

		clientThread.invoke(() -> client.getWidget(InterfaceID.MagicSpellbook.TOOLTIP).setHidden(true));
	}

	@Override
	protected void shutDown() throws Exception
	{
		if (client.getWidget(InterfaceID.MagicSpellbook.TOOLTIP) == null)
		{
			return;
		}
		clientThread.invoke(() -> client.getWidget(InterfaceID.MagicSpellbook.TOOLTIP).setHidden(false));
	}
}

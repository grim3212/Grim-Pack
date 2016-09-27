package com.grim3212.mc.pack.core.manual;

import java.util.ArrayList;
import java.util.Arrays;

import com.grim3212.mc.pack.core.manual.gui.GuiManualChapter;
import com.grim3212.mc.pack.core.manual.gui.GuiManualPage;
import com.grim3212.mc.pack.core.manual.pages.Page;

import net.minecraft.client.resources.I18n;

public class ManualChapter {

	private final String chapterId;
	private int page;
	private String partId;
	private ArrayList<Page> pages = new ArrayList<Page>();
	private ManualPart part;

	public ManualChapter(String chapterId, String partId) {
		this.chapterId = chapterId;
		this.partId = partId;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public void setPart(ManualPart part) {
		this.part = part;
	}

	public String getChapterId() {
		return chapterId;
	}

	public String getUnlocalizedName() {
		return "grimpack.manual." + this.partId + ".subsection." + this.chapterId;
	}

	public String getName() {
		return I18n.format(this.getUnlocalizedName());
	}

	public ManualChapter addPages(Page... pages) {
		this.pages.addAll(Arrays.asList(pages));

		for (int i = 0; i < pages.length; i++) {
			Page page = pages[i];
			page.setChapter(this);
			page.setTitle(I18n.format(this.getUnlocalizedName() + ".page." + page.getPageName() + ".title"));

			if (part != null)
				page.setLink(new GuiManualPage(this, new GuiManualChapter(part, this.getPage(), this.part.getPage()), i));

			// If using values set above
			if (page.setupMethod())
				page.setup();
		}

		return this;
	}

	public ArrayList<Page> getPages() {
		return this.pages;
	}
}
